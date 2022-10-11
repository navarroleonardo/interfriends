package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;

public interface ProductSizeService {

    List<ProductSize> bindSizesToProduct(Product product, List<Size> sizes);
    List<ProductSize> getProductSizesByProduct(Product product);
    List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, List<Size> requestSizes, Product product);
    void deleteByProduct(Product product);

}
