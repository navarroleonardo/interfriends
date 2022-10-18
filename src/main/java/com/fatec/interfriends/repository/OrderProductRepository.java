package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.OrderProduct;
import com.fatec.interfriends.domain.model.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
