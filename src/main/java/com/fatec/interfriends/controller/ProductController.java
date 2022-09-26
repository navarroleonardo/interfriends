package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.ProductSizeRepository;
import com.fatec.interfriends.repository.SizeRepository;
import com.fatec.interfriends.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private final SizeRepository sizeRep;
    private final ProductSizeRepository productsizeRep;

    public ProductController (ProductService productService, SizeRepository sizeRep,ProductSizeRepository productsizeRep) {
        this.productService = productService;
        this.sizeRep=sizeRep;
        this.productsizeRep= productsizeRep;
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
    
    @org.springframework.transaction.annotation.Transactional
    @GetMapping("/teste")
    public ResponseEntity<?> teste() {
    	//product created
    	ProductModel product = new ProductModel();
    	product.setName("Tenis Nike");
    	product.setDescription("Tenis nike azul");
    	product.setPrice(150.0);
    	
    	
    	//Searching Sizes direct by repository
    	// --- Must create the SizeService ---
    	Optional<SizeModel> sizeP = sizeRep.findById(1l);
    	
    	Optional<SizeModel> sizeM = sizeRep.findById(2l);
    	

    	// Inside Product, inserting size and quantity in ProductSizeModel List
    	product.addSize(sizeM.get(), 30l);
    	product.addSize(sizeP.get(), 25l);
    	
    	
    	//Save Product and cascate ProductSizeModel
    	ProductModel prod = this.productService.save(product);   	
    	
    	
    	//Reusing the variable
    	prod = this.productService.get(1l);
    	
        return ResponseEntity.ok(prod);
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
