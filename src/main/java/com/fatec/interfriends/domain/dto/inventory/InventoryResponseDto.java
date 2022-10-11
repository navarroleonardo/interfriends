package com.fatec.interfriends.domain.dto.inventory;

import com.fatec.interfriends.domain.model.ProductSize;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InventoryResponseDto {

    @NotNull
    private Long productId;
    @NotNull
    private Long sizeId;
    @NotNull
    private Long quantity;

    public InventoryResponseDto(ProductSize productSize) {
        this.productId = productSize.getProduct().getProductId();
        this.sizeId = productSize.getSize().getSizeId();
        this.quantity = productSize.getQuantity();
    }

}
