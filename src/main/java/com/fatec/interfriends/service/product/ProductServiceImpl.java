package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.favorite.FavoriteRequestDto;
import com.fatec.interfriends.domain.dto.favorite.FavoriteResponseDto;
import com.fatec.interfriends.domain.dto.product.ProductRequestDto;
import com.fatec.interfriends.domain.dto.product.ProductResponseDto;
import com.fatec.interfriends.domain.model.*;
import com.fatec.interfriends.repository.ProductCriteriaRepository;
import com.fatec.interfriends.repository.ProductRepository;
import com.fatec.interfriends.repository.query.ProductPage;
import com.fatec.interfriends.repository.query.ProductSearchCriteria;
import com.fatec.interfriends.service.category.CategoryService;
import com.fatec.interfriends.service.size.SizeService;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final UserService userService;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductCriteriaRepository productCriteriaRepository,
            SizeService sizeService,
            ProductSizeService productSizeService,
            CategoryService categoryService,
            UserService userService
    ) {
        this.productRepository = productRepository;
        this.productCriteriaRepository = productCriteriaRepository;
        this.sizeService = sizeService;
        this.productSizeService = productSizeService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        addCategories(product, productRequestDto.getCategories());
        product = this.productRepository.save(product);
        Size size = this.sizeService.getSize(productRequestDto.getSize());
        Long quantity = productRequestDto.getQuantity();
        List<ProductSize> productSizes = this.productSizeService.bindSizeToProduct(product, size, quantity);
        product.setProductSizes(productSizes);

        return new ProductResponseDto(product);
    }
    
    @Override
    public ProductResponseDto getProduct(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        return new ProductResponseDto(optionalProduct.get());
    }

    @Override
    public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        return this.productCriteriaRepository.findAllWithFilters(productPage, productSearchCriteria);
    }

    @Override
    public Page<Product> searchProducts(Pageable pageable, List<Long> categoriesId, List<Long> sizesId) {
        return this.productRepository.findDistinctByCategoriesInAndSizesIn(categoriesId, sizesId, pageable);
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
        Size requestSize = this.sizeService.getSize(productRequestDto.getSize());

        List<ProductSize> productSizes =  this.productSizeService.updateSizesOfProduct(persistentSizes, requestSize, requestProduct);
        requestProduct.setProductSizes(productSizes);

        return new ProductResponseDto(requestProduct);
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        this.productSizeService.deleteByProduct(optionalProduct.get());
        this.productRepository.delete(optionalProduct.get());

        return new ProductResponseDto(optionalProduct.get());
    }

    @Override
    public FavoriteResponseDto favoriteProduct(FavoriteRequestDto favoriteRequestDto) {
        User user = this.userService.getUser(favoriteRequestDto.getUserId());

        Optional<Product> optionalProduct = this.productRepository.findById(favoriteRequestDto.getProductId());

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        if (optionalProduct.get().getFavoritedBy().contains(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já possui o produto na lista de favoritos.");
        }

        optionalProduct.get().getFavoritedBy().add(user);

        this.productRepository.save(optionalProduct.get());

        return new FavoriteResponseDto(favoriteRequestDto);
    }

    @Override
    public Page<Product> getFavoriteProducts(Long userId, Pageable pageable) {
        User user = this.userService.getUser(userId);

        return this.productRepository.findAllByFavoritedBy(user, pageable);
    }

    @Override
    public FavoriteResponseDto disfavorProduct(FavoriteRequestDto favoriteRequestDto) {
        User user = this.userService.getUser(favoriteRequestDto.getUserId());

        Optional<Product> optionalProduct = this.productRepository.findById(favoriteRequestDto.getProductId());

        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        if (!optionalProduct.get().getFavoritedBy().contains(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário não possui o produto na lista de favoritos.");
        }

        optionalProduct.get().getFavoritedBy().remove(user);

        this.productRepository.save(optionalProduct.get());

        return new FavoriteResponseDto(favoriteRequestDto);
    }

    private void addCategories(Product product, List<Long> categoriesId) {
        List<Category> categories = new ArrayList<>();
        categoriesId.forEach(categoryId -> categories.add(this.categoryService.getCategory(categoryId)));
        product.setCategories(categories);
    }
}
