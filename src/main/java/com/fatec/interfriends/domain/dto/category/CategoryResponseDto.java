package com.fatec.interfriends.domain.dto.category;

import com.fatec.interfriends.domain.model.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryResponseDto {

    private Long categoryId;
    private String name;

    public CategoryResponseDto(Category category) {
        BeanUtils.copyProperties(category, this);
    }

}
