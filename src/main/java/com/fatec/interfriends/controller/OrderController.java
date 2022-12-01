package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.order.OrderRequestDto;
import com.fatec.interfriends.domain.dto.order.OrderResponseDto;
import com.fatec.interfriends.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @RolesAllowed("ROLE_USER")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponseDto(this.orderService.createOrder(orderRequestDto)));
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/{orderId}")
    @Transactional
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable(value = "orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponseDto(this.orderService.getOrder(orderId)));
    }
    
    @RolesAllowed("ROLE_USER")
    @GetMapping("/user/{orderId}")
    @Transactional
    public ResponseEntity<List<OrderResponseDto>> getOrders(@PathVariable(value = "orderId") Long userId) {	
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponseDto().parseOrders(this.orderService.getOrders(userId)));
    }
    
    

}
