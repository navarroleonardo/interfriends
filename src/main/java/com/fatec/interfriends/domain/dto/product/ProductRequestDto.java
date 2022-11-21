package com.fatec.interfriends.domain.dto.product;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductRequestDto {

    @NotNull
    private String name;
    @NotNull
    private Double price;
    private String description;
    private Long size;
    private List<Long> categories;
    private byte[] image;
    private Long quantity;
}
