package com.fatec.interfriends.repository;

import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSizeModel;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeModel, ProductSizeId> {
	
	List<ProductSizeModel> findAllByProduct(ProductModel product);
	void deleteByProduct(ProductModel productModel);

}
