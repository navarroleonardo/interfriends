package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDto {

    private Long productId;
    private String name;
    private Double price;
    private String description;
    private List<ProductSizeResponseDto> sizes = new ArrayList<>();

    public ProductResponseDto(ProductModel productModel, List<ProductSizeModel> productSizeModels) {
        BeanUtils.copyProperties(productModel, this);
        productSizeModels.forEach(productSizeModel -> this.sizes.add(new ProductSizeResponseDto(productSizeModel)));
    }
}
