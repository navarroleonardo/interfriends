package com.fatec.interfriends.service.order;

import java.util.List;

import com.fatec.interfriends.domain.dto.order.OrderRequestDto;
import com.fatec.interfriends.domain.model.Order;

public interface OrderService {

    Order createOrder(OrderRequestDto orderRequestDto);
    Order getOrder(Long orderId);
    List<Order> getOrders(Long userId);

}
