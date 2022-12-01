package com.fatec.interfriends.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductRequestDto {

    @NotNull
    private String name;
    @NotNull
    private Double price;
    private String description;
    private List<Long> categories;
    private byte[] image;
    private List<ProductSizeRequestDto> sizeQuantity;
}
