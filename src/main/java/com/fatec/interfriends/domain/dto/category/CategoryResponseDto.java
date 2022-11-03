package com.fatec.interfriends.domain.dto.category;

import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.Category;
import com.fatec.interfriends.domain.model.Size;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

@Data
public class CategoryResponseDto {

    private Long categoryId;
    private String name;
    private String description;

    public CategoryResponseDto(Category category) {
        BeanUtils.copyProperties(category, this);
    }
    
    public static List<CategoryResponseDto> CategoriesResponseDto(List<Category> category){
    	return category.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
    }

}
