package com.fatec.interfriends.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ProductSizeId implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private Long productId;
    private Long sizeId;

    public ProductSizeId(Long productId, Long sizeId) {
        super();
        this.productId = productId;
        this.sizeId = sizeId;
    }
 
}


