package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(productRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getProduct(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> alterProduct(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.updateProduct(id, productRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.deleteProduct(id));
    }

}
