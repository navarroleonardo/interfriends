package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSize;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeId> {
	
	List<ProductSize> findAllByProduct(Product product);
	void deleteByProduct(Product product);

}
