package com.fatec.interfriends.domain.dto.product;

import com.fatec.interfriends.domain.model.SizeModel;

import lombok.Data;

@Data
public class SizeQuantityDto {

	private SizeModel size;
	private Long quantity;
	
}
