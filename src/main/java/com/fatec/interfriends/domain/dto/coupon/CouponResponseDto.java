package com.fatec.interfriends.domain.dto.coupon;

import com.fatec.interfriends.domain.model.Coupon;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
public class CouponResponseDto {

    private UUID couponId;
    private Boolean valid;
    private String expirationDate;
    private Double savingsPercentage;

    public CouponResponseDto(Coupon coupon) {
        BeanUtils.copyProperties(coupon, this);
    }

}
