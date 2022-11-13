package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Coupon;
import com.fatec.interfriends.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {

        List<Coupon> findByUserAndValid(User user, Boolean valid);

}
