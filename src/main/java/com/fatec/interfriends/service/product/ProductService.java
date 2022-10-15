package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProduct(Long id);
    Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria);
    Page<Product> searchProducts(Pageable pageable, List<Long> categoriesId, List<Long> sizesId);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
    ProductResponseDto deleteProduct(Long id);

}
