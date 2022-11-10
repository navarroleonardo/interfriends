package com.fatec.interfriends.domain.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequestDto {

    private String name;
    private Double price;
    private String description;
    private List<Long> sizes;
    private List<Long> categories;
}
