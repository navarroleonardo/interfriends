package com.fatec.interfriends.service.order;

import com.fatec.interfriends.domain.dto.order.OrderProductRequestDto;
import com.fatec.interfriends.domain.dto.order.OrderRequestDto;
import com.fatec.interfriends.domain.model.*;
import com.fatec.interfriends.domain.model.Address;
import com.fatec.interfriends.gateway.client.PagSeguroClient;
import com.fatec.interfriends.gateway.client.dto.charge.*;
import com.fatec.interfriends.repository.OrderProductRepository;
import com.fatec.interfriends.repository.OrderRepository;
import com.fatec.interfriends.service.address.AddressService;
import com.fatec.interfriends.service.coupon.CouponService;
import com.fatec.interfriends.service.inventory.InventoryService;
import com.fatec.interfriends.service.product.ProductSizeService;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class OrderServiceImpl implements OrderService {

    private final String pagSeguroToken = "B76F0EDB934640D1BAE000F8246CB8C1";

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final ProductSizeService productSizeService;
    private final InventoryService inventoryService;
    private final CouponService couponService;
    private final PagSeguroClient pagSeguroClient;

    public OrderServiceImpl (
            OrderRepository orderRepository,
            OrderProductRepository orderProductRepository,
            UserService userService,
            AddressService addressService,
            ProductSizeService productSizeService,
            InventoryService inventoryService,
            CouponService couponService,
            PagSeguroClient pagSeguroClient
    ) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productSizeService = productSizeService;
        this.inventoryService = inventoryService;
        this.couponService = couponService;
        this.pagSeguroClient = pagSeguroClient;
    }

    @Override
    public Order createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();

        checkProductAvailability(orderRequestDto.getOrderProducts());

        assignAddressAndUserToOrder(order, orderRequestDto);
        assignCouponToOrder(order, orderRequestDto);
        debitProductsFromInventory(order, orderRequestDto.getOrderProducts());

        order.calculateTotalPrice();
        this.createCharges(order, orderRequestDto);
        this.orderRepository.save(order);

        invalidateCoupon(order.getCoupon());

        return order;
    }

    private void createCreditCardPaymentMethod(PaymentMethod paymentMethod, OrderRequestDto orderRequestDto, Order order) {
        paymentMethod.setType(orderRequestDto.getPayment().getType());
        paymentMethod.setInstallments(orderRequestDto.getPayment().getInstallments());

        Card card = new Card(orderRequestDto.getPayment().getCard(), new Holder(order.getUser().getName()));
        paymentMethod.setCard(card);
        paymentMethod.setCapture(true);
    }

    private void createDebitCardPaymentMethod(PaymentMethod paymentMethod, OrderRequestDto orderRequestDto, Order order) {

        paymentMethod.setType(orderRequestDto.getPayment().getType());

        AuthenticationMethod authenticationMethod = new AuthenticationMethod(
                "THREEDS",
                "BwABBylVaQAAAAFwllVpAAAAAAA=",
                "BwABBylVaQAAAAFwllVpAAAAAAA=",
                "05",
                "2.1.0",
                "DIR_SERVER_TID"
        );
        paymentMethod.setAuthenticationMethod(authenticationMethod);

        Card card = new Card(orderRequestDto.getPayment().getCard(), new Holder(order.getUser().getName()));
        paymentMethod.setCard(card);

    }

    private void createBoletoPaymentMethod(PaymentMethod paymentMethod, OrderRequestDto orderRequestDto, Order order) {

        User user = order.getUser();

        String dueDate = orderRequestDto.getPayment().getBoleto().getDueDate();
        Holder holder = new Holder(user.getName(), "43200227850", user.getEmail());

        Boleto boleto = new Boleto(dueDate, holder);

        paymentMethod.setType(orderRequestDto.getPayment().getType());
        paymentMethod.setBoleto(boleto);

    }

    private void createCharges(Order order, OrderRequestDto orderRequestDto) {

        String referenceId = UUID.randomUUID().toString();

        int length = String.valueOf(order.getTotalPrice()).split(Pattern.quote("."))[1].length();
        int intTotalPrice = Integer.parseInt(String.valueOf(order.getTotalPrice()).replace(".", ""));
        intTotalPrice = length == 1 ? intTotalPrice * 10 : intTotalPrice;
        Amount amount = new Amount(intTotalPrice);

        PaymentMethod paymentMethod = new PaymentMethod();

        switch (orderRequestDto.getPayment().getType()) {
            case CREDIT_CARD -> createCreditCardPaymentMethod(paymentMethod, orderRequestDto, order);
            case DEBIT_CARD -> createDebitCardPaymentMethod(paymentMethod, orderRequestDto, order);
            case BOLETO -> createBoletoPaymentMethod(paymentMethod, orderRequestDto, order);
        }

        CreateChargeRequestDto createChargeRequestDto = new CreateChargeRequestDto(
                referenceId,
                amount,
                paymentMethod
        );

        this.pagSeguroClient.createCharges(pagSeguroToken, createChargeRequestDto);

    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido n??o encontrado");
        }

        return optionalOrder.get();
    }

    private void assignAddressAndUserToOrder(Order order, OrderRequestDto orderRequestDto) {
        User user = this.userService.getUser(orderRequestDto.getUserId());
        Address address = this.addressService.getAddress(orderRequestDto.getAddressId());

        if (!user.getAddresses().contains(address)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endere??o n??o pertence ao usu??rio.");
        }

        order.setUser(user);
        order.setAddress(address);
    }

    private void checkProductAvailability(List<OrderProductRequestDto> orderProductRequestDtos) {
        orderProductRequestDtos.forEach(orderProductRequestDto -> {
            ProductSize productSize = this.productSizeService.getProductSize(new ProductSizeId(orderProductRequestDto.getProductId(), orderProductRequestDto.getSizeId()));

            if (productSize.getQuantity() < orderProductRequestDto.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade selecionada para o produto " + productSize.getProduct().getName() + " n??o est?? dispon??vel em estoque.");
            }
        });
    }

    private void debitProductsFromInventory(Order order, List<OrderProductRequestDto> orderProductRequestDtos) {
        orderProductRequestDtos.forEach(orderProductRequestDto -> {
            ProductSize productSize = this.productSizeService.getProductSize(new ProductSizeId(orderProductRequestDto.getProductId(), orderProductRequestDto.getSizeId()));
            this.inventoryService.removeProductsFromInventory(productSize, orderProductRequestDto.getQuantity());

            Product product = productSize.getProduct();
            Size size = productSize.getSize();

            OrderProduct orderProduct = this.orderProductRepository.save(new OrderProduct(order, product, size, orderProductRequestDto.getQuantity()));
            order.getOrderProducts().add(orderProduct);
            product.getOrderProducts().add(orderProduct);
            size.getOrderProducts().add(orderProduct);
        });
    }

    private void assignCouponToOrder(Order order, OrderRequestDto orderRequestDto) {
        if (orderRequestDto.getCouponId() == null) return;

        Coupon coupon = this.couponService.getCoupon(orderRequestDto.getCouponId());

        if (!coupon.getValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cupom selecionado ?? invalido.");
        }

        if (!(coupon.getUser().getUserId() == order.getUser().getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cupom selecionado n??o pertence ao usu??rio selecionado");
        }

        coupon = this.couponService.validateExpirationDate(coupon);

        if (!coupon.getValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cupom selecionado est?? expirado.");
        }

        order.setCoupon(coupon);
    }

    private void invalidateCoupon(Coupon coupon) {
        if (coupon == null) return;
        this.couponService.invalidateCoupon(coupon);
    }

	@Override
	public List<Order> getOrders(Long userId) {
		return this.orderRepository.findByUser(this.userService.getUser(userId));
	}
}
