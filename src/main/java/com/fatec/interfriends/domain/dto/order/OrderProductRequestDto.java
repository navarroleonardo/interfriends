package com.fatec.interfriends.domain.dto.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderProductRequestDto {

    @NotNull
    private Long productId;
    @NotNull
    private Long sizeId;
    @NotNull
    private Long quantity;

}
