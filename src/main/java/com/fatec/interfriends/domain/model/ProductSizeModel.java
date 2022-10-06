package com.fatec.interfriends.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product_size")
@AllArgsConstructor
public class ProductSizeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductSizeId id = new ProductSizeId();

    @ManyToOne
    @MapsId("productId")
    private ProductModel product;

    @ManyToOne
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
