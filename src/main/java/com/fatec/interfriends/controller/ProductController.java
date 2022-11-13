package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.favorite.FavoriteRequestDto;
import com.fatec.interfriends.domain.dto.favorite.FavoriteResponseDto;
import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import com.fatec.interfriends.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto){	
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(productRequestDto));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/foto", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Transactional
    public ResponseEntity<ProductResponseDto> addImage(
    		@RequestPart("file") MultipartFile[] file,
    		@RequestPart("product") @Valid ProductRequestDto productRequestDto) throws IOException{	
    	productRequestDto.setImage(file[0].getBytes());
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.createProduct(productRequestDto));
    }
    
    

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria){
    	return ResponseEntity.status(HttpStatus.OK).body(this.productService.getProducts(productPage, productSearchCriteria));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
            Pageable pageable,
            @RequestParam(value = "categories") List<Long> categoriesId,
            @RequestParam(value = "sizes") List<Long> sizesId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.searchProducts(pageable, categoriesId, sizesId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseDto> alterProduct(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.updateProduct(id, productRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.deleteProduct(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/favorite")
    @Transactional
    public ResponseEntity<FavoriteResponseDto> favoriteProduct(@Valid @RequestBody FavoriteRequestDto favoriteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.favoriteProduct(favoriteRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/favorite/{userId}")
    @Transactional
    public ResponseEntity<Page<Product>> getFavoriteProducts(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.getFavoriteProducts(userId, pageable));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/favorite")
    @Transactional
    public ResponseEntity<FavoriteResponseDto> disfavorProduct(@Valid @RequestBody FavoriteRequestDto favoriteRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.disfavorProduct(favoriteRequestDto));
    }

}
