package com.fatec.interfriends.domain.dto.size;

import com.fatec.interfriends.domain.model.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SizeQuantity {
	
	private Size size;
	private Long quantity;
}
