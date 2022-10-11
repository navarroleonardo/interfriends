package com.fatec.interfriends.domain.model;

import com.fatec.interfriends.domain.dto.category.CategoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    @Column(nullable = false, unique = true)
    private String name;

    public Category(CategoryRequestDto categoryRequestDto) {
        BeanUtils.copyProperties(categoryRequestDto, this);
    }

}
