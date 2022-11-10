package com.fatec.interfriends.service.coupon;

import com.fatec.interfriends.domain.dto.coupon.CouponRequestDto;
import com.fatec.interfriends.domain.model.Coupon;
import com.fatec.interfriends.domain.model.User;
import com.fatec.interfriends.repository.CouponRepository;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserService userService;

    public CouponServiceImpl (CouponRepository couponRepository, UserService userService) {
        this.couponRepository = couponRepository;
        this.userService = userService;
    }

    @Override
    public Coupon createCoupon(CouponRequestDto couponRequestDto) {

        User user = this.userService.getUser(couponRequestDto.getUserId());

        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String expirationDate = formatador.format(new Date(new Date().getTime() + (1000L * 60 * 60 * 24 * 30)));
        Double savingsPercentage = Math.min(0.02 * couponRequestDto.getProductsQuantity(), 0.25);

        Coupon coupon = new Coupon(user, true, expirationDate, savingsPercentage);

        return this.couponRepository.save(coupon);
    }
}
