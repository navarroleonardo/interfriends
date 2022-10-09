package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.ProductCriteriaRepository;
import com.fatec.interfriends.repository.ProductRepository;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import com.fatec.interfriends.service.size.SizeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCriteriaRepository productCriteriaRepository;
    private final SizeService sizeService;
    private final ProductSizeService productSizeService;

    public ProductServiceImpl(ProductRepository productRepository, ProductCriteriaRepository productCriteriaRepository, SizeService sizeService, ProductSizeService productSizeService) {
        this.productRepository = productRepository;
        this.productCriteriaRepository = productCriteriaRepository;
        this.sizeService = sizeService;
        this.productSizeService = productSizeService;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        ProductModel productModel = new ProductModel(productRequestDto);
        productModel = this.productRepository.save(productModel);

        List<SizeModel> sizeModels = this.sizeService.getSizesById(productRequestDto.getSizes());
        List<ProductSizeModel> productSizeModels = this.productSizeService.bindSizesToProduct(productModel, sizeModels);

        return new ProductResponseDto(productModel, productSizeModels);
    }
    
    @Override
    public ProductResponseDto getProduct(Long id) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (productModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        List<ProductSizeModel> productSizeModels = this.productSizeService.getProductSizesByProduct(productModelOptional.get());

        return new ProductResponseDto(productModelOptional.get(), productSizeModels);
    }

    @Override
    public Page<ProductModel> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        return this.productCriteriaRepository.findAllWithFilters(productPage, productSearchCriteria);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (productModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        ProductModel persistentProductModel = productModelOptional.get();
        ProductModel requestProductModel = new ProductModel(productRequestDto);

        requestProductModel.setProductId(persistentProductModel.getProductId());

        requestProductModel = this.productRepository.save(requestProductModel);

        List<ProductSizeModel> persistentProductSizeModels = this.productSizeService.getProductSizesByProduct(persistentProductModel);
        List<SizeModel> persistentSizeModels = this.sizeService.getSizesByProductSizes(persistentProductSizeModels);
        List<SizeModel> requestSizeModels = this.sizeService.getSizesById(productRequestDto.getSizes());

        List<ProductSizeModel> productSizeModels =  this.productSizeService.updateSizesOfProduct(persistentSizeModels, requestSizeModels, requestProductModel);

        return new ProductResponseDto(requestProductModel, productSizeModels);
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Optional<ProductModel> productModelOptional = this.productRepository.findById(id);

        if (productModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        List<ProductSizeModel> productSizeModels = this.productSizeService.getProductSizesByProduct(productModelOptional.get());
        this.productSizeService.deleteByProduct(productModelOptional.get());
        this.productRepository.delete(productModelOptional.get());

        return new ProductResponseDto(productModelOptional.get(), productSizeModels);
    }
}
