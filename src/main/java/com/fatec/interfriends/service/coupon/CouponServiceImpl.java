package com.fatec.interfriends.service.coupon;

import com.fatec.interfriends.domain.dto.coupon.CouponRequestDto;
import com.fatec.interfriends.domain.model.Coupon;
import com.fatec.interfriends.domain.model.User;
import com.fatec.interfriends.repository.CouponRepository;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        if (couponRequestDto.getProductsQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade de produtos inválida.");
        }

        User user = this.userService.getUser(couponRequestDto.getUserId());

        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String expirationDate = formatador.format(new Date(new Date().getTime() + (1000L * 60 * 60 * 24 * 30)));
        Double savingsPercentage = Math.min(0.02 * couponRequestDto.getProductsQuantity(), 0.25);

        Coupon coupon = new Coupon(user, true, expirationDate, savingsPercentage);

        return this.couponRepository.save(coupon);

    }

    @Override
    public Coupon invalidateCoupon(Coupon coupon) {
        coupon.setValid(false);
        return this.couponRepository.save(coupon);
    }

    @Override
    public Coupon getCoupon(UUID couponId) {

        Optional<Coupon> optionalCoupon = this.couponRepository.findById(couponId);

        if (!optionalCoupon.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupom não encontrado.");
        }

        return optionalCoupon.get();

    }

    @Override
    public List<Coupon> getCouponsByUser(Long userId) {

        User user = this.userService.getUser(userId);
        List<Coupon> coupons = this.couponRepository.findByUserAndValid(user, true);

        return coupons.stream()
                .map(this::validateExpirationDate)
                .filter(Coupon::getValid)
                .toList();

    }

    @Override
    public Coupon validateExpirationDate(Coupon coupon) {
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
            if (formatador.parse(coupon.getExpirationDate()).compareTo(formatador.parse(formatador.format(new Date(new Date().getTime())))) < 0) {
                return this.invalidateCoupon(coupon);
            }
        } catch (ParseException ignored) { }

        return coupon;
    }
}
