package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Order;
import com.fatec.interfriends.domain.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUser(User user);
}
