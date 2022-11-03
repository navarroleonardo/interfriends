package com.fatec.interfriends.service.order;

import com.fatec.interfriends.domain.dto.order.OrderProductRequestDto;
import com.fatec.interfriends.domain.dto.order.OrderRequestDto;
import com.fatec.interfriends.domain.model.*;
import com.fatec.interfriends.repository.OrderProductRepository;
import com.fatec.interfriends.repository.OrderRepository;
import com.fatec.interfriends.service.address.AddressService;
import com.fatec.interfriends.service.inventory.InventoryService;
import com.fatec.interfriends.service.product.ProductSizeService;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final ProductSizeService productSizeService;
    private final InventoryService inventoryService;

    public OrderServiceImpl (
            OrderRepository orderRepository,
            OrderProductRepository orderProductRepository,
            UserService userService,
            AddressService addressService,
            ProductSizeService productSizeService,
            InventoryService inventoryService
    ) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productSizeService = productSizeService;
        this.inventoryService = inventoryService;
    }

    @Override
    public Order createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();

        checkProductAvailability(orderRequestDto.getOrderProducts());

        assignAddressAndUserToOrder(order, orderRequestDto);
        this.orderRepository.save(order);

        debitProductsFromInventory(order, orderRequestDto.getOrderProducts());

        order.calculateTotalPrice();
        this.orderRepository.save(order);

        return order;
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }

        return optionalOrder.get();
    }

    private void assignAddressAndUserToOrder(Order order, OrderRequestDto orderRequestDto) {
        User user = this.userService.getUser(orderRequestDto.getUserId());
        Address address = this.addressService.getAddress(orderRequestDto.getAddressId());

        if (!user.getAddresses().contains(address)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço não pertence ao usuário.");
        }

        order.setUser(user);
        order.setAddress(address);
    }

    private void checkProductAvailability(List<OrderProductRequestDto> orderProductRequestDtos) {
        orderProductRequestDtos.forEach(orderProductRequestDto -> {
            ProductSize productSize = this.productSizeService.getProductSize(new ProductSizeId(orderProductRequestDto.getProductId(), orderProductRequestDto.getSizeId()));

            if (productSize.getQuantity() < orderProductRequestDto.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade selecionada para o produto " + productSize.getProduct().getName() + " não está disponível em estoque.");
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
}