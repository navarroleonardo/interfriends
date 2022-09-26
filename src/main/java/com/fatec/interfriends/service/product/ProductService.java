package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProduct(Long id);
    ProductResponseDto updateProduct(Long id, ProductRequestDto userRequestDto);
    ProductResponseDto deleteProduct(Long id);
    ProductModel save(ProductModel product);
    ProductModel get(Long id);
    List<ProductSizeModel> getSizeAndQuantity(ProductRequestDto productRequestDto);


}
