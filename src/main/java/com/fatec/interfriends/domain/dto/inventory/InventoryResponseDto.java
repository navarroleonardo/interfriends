package com.fatec.interfriends.domain.dto.inventory;

import com.fatec.interfriends.domain.model.ProductSize;
import lombok.Data;

@Data
public class InventoryResponseDto {

    private Long productId;
    private Long sizeId;
    private Long quantity;

    public InventoryResponseDto(ProductSize productSize) {
        this.productId = productSize.getProduct().getProductId();
        this.sizeId = productSize.getSize().getSizeId();
        this.quantity = productSize.getQuantity();
    }

}
