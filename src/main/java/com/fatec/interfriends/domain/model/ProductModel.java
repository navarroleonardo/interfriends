package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.interfriends.domain.dto.product.ProductRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"product", "id"}, allowSetters = true)
    private List<ProductSizeModel> sizes = new ArrayList<>();

    public ProductModel(ProductRequestDto productRequestDto) {
        BeanUtils.copyProperties(productRequestDto, this);
    }

    public void addSize(SizeModel sizeModel) {
        sizes.add(new ProductSizeModel(this, sizeModel));
    }
    
    public void addSize(SizeModel sizeModel, Long quantity) {
        sizes.add(new ProductSizeModel(this, sizeModel, quantity));
    }
    
    
    public void removeSize(SizeModel sizeModel) {
        sizes.remove(new ProductSizeModel(this, sizeModel));
    }

}
