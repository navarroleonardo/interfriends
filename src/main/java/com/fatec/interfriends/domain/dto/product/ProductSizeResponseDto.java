package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.ProductSizeModel;

import lombok.Data;

@Data
public class ProductSizeResponseDto {

	private SizeResponseDto size;
	private Long quantity;

	public ProductSizeResponseDto(ProductSizeModel productSizeModel) {
		this.size = new SizeResponseDto(productSizeModel.getSize());
		this.quantity = productSizeModel.getQuantity();
	}

}
