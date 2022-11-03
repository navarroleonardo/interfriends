package com.fatec.interfriends.service.category;

import java.util.List;

import com.fatec.interfriends.domain.dto.category.CategoryRequestDto;
import com.fatec.interfriends.domain.model.Category;

public interface CategoryService {

    Category createCategory(CategoryRequestDto categoryRequestDto);
    List<Category> getAllCategories();
    Category getCategory(Long categoryId);
    Category updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
    Category deleteCategory(Long categoryId);

}
