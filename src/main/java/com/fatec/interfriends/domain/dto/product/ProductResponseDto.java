package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.dto.category.CategoryResponseDto;
import com.fatec.interfriends.domain.model.Product;
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
    private List<CategoryResponseDto> categories = new ArrayList<>();
    private byte[] image;

    public ProductResponseDto(Product product) {
        BeanUtils.copyProperties(product, this);
        product.getProductSizes().forEach(productSize -> this.sizes.add(new ProductSizeResponseDto(productSize)));
        product.getCategories().forEach(category -> this.categories.add(new CategoryResponseDto(category)));
    }
}
