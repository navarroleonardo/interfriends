package com.fatec.interfriends.domain.dto.inventory;

import com.fatec.interfriends.domain.model.ProductSizeModel;
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

    public InventoryResponseDto(ProductSizeModel productSizeModel) {
        this.productId = productSizeModel.getProduct().getProductId();
        this.sizeId = productSizeModel.getSize().getSizeId();
        this.quantity = productSizeModel.getQuantity();
    }

}
