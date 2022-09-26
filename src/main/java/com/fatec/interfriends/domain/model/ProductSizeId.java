package com.fatec.interfriends.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeId implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private Long productId;
    private Long sizeId;
 
}


