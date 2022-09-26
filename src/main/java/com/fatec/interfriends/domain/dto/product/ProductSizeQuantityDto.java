package com.fatec.interfriends.domain.dto.product;
import java.util.*;

import com.fatec.interfriends.domain.model.SizeModel;
import lombok.Data;

@Data
public class ProductSizeQuantityDto {
	
	private Long productId;
    private String name;
    private Double price;
    private String description;
    private List<SizeQuantityDto> sizes = new ArrayList<SizeQuantityDto>();

    

}
