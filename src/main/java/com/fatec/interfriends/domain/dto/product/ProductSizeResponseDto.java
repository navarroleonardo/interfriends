package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.ProductSize;

import lombok.Data;

@Data
public class ProductSizeResponseDto {

	private SizeResponseDto size;
	private Long quantity;

	public ProductSizeResponseDto(ProductSize productSize) {
		this.size = new SizeResponseDto(productSize.getSize());
		this.quantity = productSize.getQuantity();
	}

}
