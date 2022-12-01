package com.fatec.interfriends.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "order_product")
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderProductId id = new OrderProductId();

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    private Size size;

    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private Double price;

    public OrderProduct(Order order, Product product, Size size, Long quantity) {
        this.order = order;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

}
