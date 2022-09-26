package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.ProductModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	
	Optional<ProductModel> findById(Long id);
}
