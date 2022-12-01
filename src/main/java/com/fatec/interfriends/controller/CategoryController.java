package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.category.CategoryRequestDto;
import com.fatec.interfriends.domain.dto.category.CategoryResponseDto;
import com.fatec.interfriends.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryResponseDto(this.categoryService.createCategory(categoryRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponseDto(this.categoryService.getCategory(categoryId)));
    }
    
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK)
        	   .body(CategoryResponseDto.CategoriesResponseDto(this.categoryService.getAllCategories()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable(value = "id") Long id, @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponseDto(this.categoryService.updateCategory(id, categoryRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(new CategoryResponseDto(this.categoryService.deleteCategory(categoryId)));
    }

}
