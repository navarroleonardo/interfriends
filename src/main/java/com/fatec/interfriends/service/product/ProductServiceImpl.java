package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.Category;
import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;
import com.fatec.interfriends.repository.ProductCriteriaRepository;
import com.fatec.interfriends.repository.ProductRepository;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import com.fatec.interfriends.service.category.CategoryService;
import com.fatec.interfriends.service.size.SizeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCriteriaRepository productCriteriaRepository;
    private final SizeService sizeService;
    private final ProductSizeService productSizeService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ProductCriteriaRepository productCriteriaRepository, SizeService sizeService, ProductSizeService productSizeService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productCriteriaRepository = productCriteriaRepository;
        this.sizeService = sizeService;
        this.productSizeService = productSizeService;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = new Product(productRequestDto);
        addCategories(product, productRequestDto.getCategories());
        product = this.productRepository.save(product);

        List<Size> sizes = this.sizeService.getSizesById(productRequestDto.getSizes());
        List<ProductSize> productSizes = this.productSizeService.bindSizesToProduct(product, sizes);

        return new ProductResponseDto(product, productSizes);
    }
    
    @Override
    public ProductResponseDto getProduct(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        List<ProductSize> productSizes = this.productSizeService.getProductSizesByProduct(optionalProduct.get());

        return new ProductResponseDto(optionalProduct.get(), productSizes);
    }

    @Override
    public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        return this.productCriteriaRepository.findAllWithFilters(productPage, productSearchCriteria);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        Product persistentProduct = optionalProduct.get();
        Product requestProduct = new Product(productRequestDto);

        requestProduct.setProductId(persistentProduct.getProductId());

        addCategories(requestProduct, productRequestDto.getCategories());
        requestProduct = this.productRepository.save(requestProduct);

        List<ProductSize> persistentProductSizes = this.productSizeService.getProductSizesByProduct(persistentProduct);
        List<Size> persistentSizes = this.sizeService.getSizesByProductSizes(persistentProductSizes);
        List<Size> requestSizes = this.sizeService.getSizesById(productRequestDto.getSizes());

        List<ProductSize> productSizes =  this.productSizeService.updateSizesOfProduct(persistentSizes, requestSizes, requestProduct);

        return new ProductResponseDto(requestProduct, productSizes);
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        List<ProductSize> productSizes = this.productSizeService.getProductSizesByProduct(optionalProduct.get());
        this.productSizeService.deleteByProduct(optionalProduct.get());
        this.productRepository.delete(optionalProduct.get());

        return new ProductResponseDto(optionalProduct.get(), productSizes);
    }

    private void addCategories(Product product, List<Long> categoriesId) {
        List<Category> categories = new ArrayList<>();
        categoriesId.forEach(categoryId -> categories.add(this.categoryService.getCategory(categoryId)));
        product.setCategories(categories);
    }
}
