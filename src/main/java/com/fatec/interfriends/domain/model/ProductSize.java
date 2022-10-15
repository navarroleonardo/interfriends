package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product_size")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSize implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductSizeId id = new ProductSizeId();

    @ManyToOne
    @MapsId("productId")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    private Size size;
    
    private Long quantity = 0L;
    
    public ProductSize(Product product, Size size) {
		this.product = product;
		this.size = size;
	}
    
    public ProductSize(Product product, Size size, Long quantity) {
		this.product = product;
		this.size = size;
		this.quantity = quantity;
	}

}
