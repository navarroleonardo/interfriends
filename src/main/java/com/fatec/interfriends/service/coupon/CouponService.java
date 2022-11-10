package com.fatec.interfriends.service.coupon;

import com.fatec.interfriends.domain.dto.coupon.CouponRequestDto;
import com.fatec.interfriends.domain.model.Coupon;

public interface CouponService {

    Coupon createCoupon(CouponRequestDto couponRequestDto);

}
