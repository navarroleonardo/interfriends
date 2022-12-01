package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.dto.product.ProductSizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeQuantity;
import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.Size;

public interface ProductSizeService {

    List<ProductSize> bindSizesToProduct(Product product, List<ProductSizeRequestDto> productSizes);
    List<ProductSize> bindSizeToProduct(Product product, Size size, Long quantity);
    List<ProductSize> getProductSizesByProduct(Product product);
    List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, Size requestSizes, Product product);
    List<ProductSize> updateSizesOfProduct(List<SizeQuantity> persistentProductSizes,  Product product);
    ProductSize getProductSize(ProductSizeId productSizeId);
    void deleteByProduct(Product product);

}
