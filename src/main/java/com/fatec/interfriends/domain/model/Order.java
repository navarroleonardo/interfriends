package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_table")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(nullable = true)
    private Double totalPrice;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    public void calculateTotalPrice() {
        double totalPrice = 0.0;
        for (OrderProduct orderProduct : this.getOrderProducts()) {
            totalPrice = totalPrice + orderProduct.getPrice();
        }
        if (this.coupon != null) {
            totalPrice = totalPrice * (1 - coupon.getSavingsPercentage());
        }
        this.totalPrice = totalPrice;
    }

}
