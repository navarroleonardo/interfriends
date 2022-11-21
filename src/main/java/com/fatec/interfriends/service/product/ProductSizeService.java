package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.Size;

public interface ProductSizeService {

    List<ProductSize> bindSizesToProduct(Product product, List<Size> sizes);
    List<ProductSize> bindSizeToProduct(Product product, Size size, Long quantity);
    List<ProductSize> getProductSizesByProduct(Product product);
    List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, Size requestSizes, Product product);
    ProductSize getProductSize(ProductSizeId productSizeId);
    void deleteByProduct(Product product);

}
