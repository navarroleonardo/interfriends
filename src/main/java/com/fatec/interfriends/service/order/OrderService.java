package com.fatec.interfriends.service.order;

import com.fatec.interfriends.domain.dto.order.OrderRequestDto;
import com.fatec.interfriends.domain.model.Order;

public interface OrderService {

    Order createOrder(OrderRequestDto orderRequestDto);
    Order getOrder(Long orderId);

}
