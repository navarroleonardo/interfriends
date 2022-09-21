package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.model.ProductModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProductResponseDto {

    private Long productId;
    private String name;
    private Double price;
    private String description;

    public ProductResponseDto(ProductModel productModel) {
        BeanUtils.copyProperties(productModel, this);
    }
}
