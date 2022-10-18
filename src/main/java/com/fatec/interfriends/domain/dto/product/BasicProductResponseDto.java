package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.model.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BasicProductResponseDto {

    private Long productId;
    private String name;
    private Double price;
    private String description;

    public BasicProductResponseDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }

}
