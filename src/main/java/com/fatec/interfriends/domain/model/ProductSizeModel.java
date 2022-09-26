package com.fatec.interfriends.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products_sizes")
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
    
    private Long quantity = 0L;
    
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
