package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.model.ProductSize;
import lombok.Data;

@Data
public class ProductSizeRequestDto {

	private Long sizeId;
	private Long quantity;

	public ProductSizeRequestDto(ProductSize productSize) {
		this.sizeId = productSize.getSize().getSizeId();
		this.quantity = productSize.getQuantity();
	}
	
	public ProductSizeRequestDto(Long sizeId, Long quantity) {
		this.sizeId = sizeId;
		this.quantity = quantity;
	}

}
