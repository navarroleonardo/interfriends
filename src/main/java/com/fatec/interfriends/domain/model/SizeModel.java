package com.fatec.interfriends.domain.model;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "size")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;
    @Column(nullable = false)
    private String size;
    @Column(nullable = true)
    private String description;

    @OneToMany
    private Set<ProductSizeModel> productSizes = new HashSet<>();

    public SizeModel(SizeRequestDto sizeRequestDto) {
        BeanUtils.copyProperties(sizeRequestDto, this);
    }

}
