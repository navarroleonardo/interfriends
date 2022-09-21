package com.fatec.interfriends.domain.model;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = true)
    private String description;

    /*@OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
            )
    private Set<ProductSizeModel> sizes;*/

    public ProductModel(ProductRequestDto productRequestDto) {
        BeanUtils.copyProperties(productRequestDto, this);
        /*this.sizes = new HashSet<>();*/
    }

    /*public void addSizes(List<SizeModel> sizeModels) {
        sizeModels.forEach((sizeModel -> {
            ProductSizeModel productSizeModel = new ProductSizeModel(this, sizeModel, 0L);
            sizes.add(productSizeModel);
        }));
    }

    public void removeSize(SizeModel sizeModel) {
        for (Iterator<ProductSizeModel> iterator = this.sizes.iterator(); iterator.hasNext();) {
            ProductSizeModel productSizeModel = iterator.next();

            if(productSizeModel.getProduct().equals(this) && productSizeModel.getSize().equals(sizeModel)) {
                iterator.remove();
                productSizeModel.setProduct(null);
                productSizeModel.setSize(null);
            }
        }
    }*/

}
