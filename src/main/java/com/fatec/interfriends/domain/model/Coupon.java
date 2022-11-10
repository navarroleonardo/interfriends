package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(36)")
    private UUID couponId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    Boolean valid;

    @Column(nullable = false)
    String expirationDate;

    @Column(nullable = false)
    Double savingsPercentage;

    public Coupon (User user, Boolean valid, String expirationDate, Double savingsPercentage) {
        this.user = user;
        this.valid = valid;
        this.expirationDate = expirationDate;
        this.savingsPercentage = savingsPercentage;
    }

}
