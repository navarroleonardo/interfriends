package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.coupon.CouponRequestDto;
import com.fatec.interfriends.domain.dto.coupon.CouponResponseDto;
import com.fatec.interfriends.service.coupon.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController (CouponService couponService) {
        this.couponService = couponService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<CouponResponseDto> createCoupon(@RequestBody @Valid CouponRequestDto couponRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CouponResponseDto(this.couponService.createCoupon(couponRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    @Transactional
    public ResponseEntity<List<CouponResponseDto>> getCouponsByUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.couponService.getCouponsByUser(userId).stream().map(CouponResponseDto::new).toList()
        );
    }

}
