package com.fatec.interfriends.service.product;

import java.util.List;

import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;

public interface ProductSizeService {

    List<ProductSizeModel> bindSizesToProduct(ProductModel productModel, List<SizeModel> sizeModels);
    List<ProductSizeModel> getProductSizesByProduct(ProductModel productModel);
    List<ProductSizeModel> updateSizesOfProduct(List<SizeModel> persistentProductSizeModels, List<SizeModel> requestSizeModels, ProductModel productModel);
    void deleteByProduct(ProductModel productModel);

}
