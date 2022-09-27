package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.ProductRepository;
import com.fatec.interfriends.repository.SizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;

    public ProductServiceImpl(ProductRepository productRepository, SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        ProductModel productModel = new ProductModel(productRequestDto);

        this.attachSizesToProduct(productModel, productRequestDto.getSizes());

        return new ProductResponseDto(this.productRepository.save(productModel));
    }
    
    @Override
    public ProductResponseDto getProduct(Long id) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (!productModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        return new ProductResponseDto(productModelOptional.get());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (!productModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        ProductModel productModel = new ProductModel(productRequestDto);

        productModel.setProductId(productModelOptional.get().getProductId());

        return new ProductResponseDto(this.productRepository.save(productModel));
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (!productModelOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        this.productRepository.delete(productModelOptional.get());

        return new ProductResponseDto(productModelOptional.get());
    }

    private void attachSizesToProduct(ProductModel productModel, List<Long> sizeIds) {
        sizeIds.forEach((sizeId) -> {
            Optional<SizeModel> sizeModelOptional = this.sizeRepository.findById(sizeId);

            if (sizeModelOptional.isPresent()) {
                productModel.addSize(sizeModelOptional.get());
            }
        });
    }
}
