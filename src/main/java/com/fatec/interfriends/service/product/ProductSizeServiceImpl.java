package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.ProductRepository;
import com.fatec.interfriends.repository.ProductSizeRepository;
//import com.fatec.interfriends.repository.ProductSizeRepository;
import com.fatec.interfriends.repository.SizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;


    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

	@Override
	public List<ProductSizeModel> getProduct(ProductModel product) {
		return this.productSizeRepository.findByProduct(product);
	}

	@Override
	public ProductResponseDto updateProductSize(Long id, ProductRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponseDto deleteProductSize(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
