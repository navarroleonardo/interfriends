package com.fatec.interfriends.domain.dto.inventory;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InventoryRequestDto {

    @NotNull
    private Long productId;
    @NotNull
    private Long sizeId;
    @NotNull
    private Long quantity;

}
