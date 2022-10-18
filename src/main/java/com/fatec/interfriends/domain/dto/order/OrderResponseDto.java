package com.fatec.interfriends.domain.dto.order;

import com.fatec.interfriends.domain.dto.address.AddressResponseDto;
import com.fatec.interfriends.domain.dto.user.BasicUserResponseDto;
import com.fatec.interfriends.domain.model.Order;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long orderId;
    private BasicUserResponseDto user;
    private AddressResponseDto address;
    private List<OrderProductResponseDto> orderProducts = new ArrayList<>();
    private Double totalPrice;


    public OrderResponseDto(Order order) {
        BeanUtils.copyProperties(order, this);
        this.setUser(new BasicUserResponseDto(order.getUser()));
        this.setAddress(new AddressResponseDto(order.getAddress()));
        order.getOrderProducts().forEach(orderProduct -> this.orderProducts.add(new OrderProductResponseDto(orderProduct)));
    }

}
