package com.fatec.interfriends.service.category;

import com.fatec.interfriends.domain.dto.category.CategoryRequestDto;
import com.fatec.interfriends.domain.model.Category;
import com.fatec.interfriends.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        Optional<Category> optionalCategory = this.categoryRepository.findByName(categoryRequestDto.getName());

        if (optionalCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A categoria informada já está cadastrada.");
        }

        Category category = new Category(categoryRequestDto);

        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);

        if (!optionalCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
        }

        return optionalCategory.get();
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);

        if (!optionalCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
        }

        Category category = new Category(categoryRequestDto);
        category.setCategoryId(optionalCategory.get().getCategoryId());

        return this.categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);

        if (!optionalCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
        }

        this.categoryRepository.delete(optionalCategory.get());

        return optionalCategory.get();
    }
}
