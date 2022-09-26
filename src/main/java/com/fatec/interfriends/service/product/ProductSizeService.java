package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;

public interface ProductSizeService {

    List<ProductSizeModel> getProduct(ProductModel product);
    ProductResponseDto updateProductSize(Long id, ProductRequestDto userRequestDto);
    ProductResponseDto deleteProductSize(Long id);

}
