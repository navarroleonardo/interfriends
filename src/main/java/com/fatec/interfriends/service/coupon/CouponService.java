package com.fatec.interfriends.service.coupon;

import com.fatec.interfriends.domain.dto.coupon.CouponRequestDto;
import com.fatec.interfriends.domain.model.Coupon;

import java.util.List;
import java.util.UUID;

public interface CouponService {

    Coupon createCoupon(CouponRequestDto couponRequestDto);
    Coupon invalidateCoupon(Coupon coupon);
    Coupon getCoupon(UUID couponId);
    List<Coupon> getCouponsByUser(Long userId);
    Coupon validateExpirationDate(Coupon coupon);

}
