package com.fatec.interfriends.domain.dto.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponRequestDto {

    @NotNull
    private String cpf;
    @NotNull
    private Integer productsQuantity;

}
