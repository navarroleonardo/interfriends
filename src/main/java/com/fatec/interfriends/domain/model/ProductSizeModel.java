package com.fatec.interfriends.domain.model;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product_size")
@AllArgsConstructor
public class ProductSizeModel implements Serializable {
 
    private static final long serialVersionUID = 1L;

	@EmbeddedId
    private ProductSizeId id = new ProductSizeId();
 
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    private ProductModel product;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("sizeId")
    private SizeModel size;
    
    private Long quantity = 0l;
    
    public ProductSizeModel(ProductModel productModel, SizeModel sizeModel) {
		this.product = productModel;
		this.size = sizeModel;
	}
    
    public ProductSizeModel(ProductModel productModel, SizeModel sizeModel, Long quantity) {
		this.product = productModel;
		this.size = sizeModel;
		this.quantity = quantity;
	}


}
