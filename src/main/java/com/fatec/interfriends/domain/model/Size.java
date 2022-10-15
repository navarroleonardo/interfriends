package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Size implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;
    @Column(nullable = false, unique = true)
    private String size;
    @Column(nullable = true)
    private String description;

    @OneToMany
    @JsonIgnore
    private Set<ProductSize> productSizes = new HashSet<>();

    public Size(SizeRequestDto sizeRequestDto) {
        BeanUtils.copyProperties(sizeRequestDto, this);
    }

}
