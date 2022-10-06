package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.model.ProductModel;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;
import com.fatec.interfriends.repository.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

	public List<ProductSizeModel> bindSizesToProduct(ProductModel productModel, List<SizeModel> sizeModels) {
		return sizeModels.stream()
				.map(sizeModel -> this.productSizeRepository.save(new ProductSizeModel(productModel, sizeModel)))
				.toList();
	}

	@Override
	public List<ProductSizeModel> getProductSizesByProduct(ProductModel productModel) {
		return this.productSizeRepository.findAllByProduct(productModel);
	}

	@Override
	public void deleteByProduct(ProductModel productModel) {
		this.productSizeRepository.deleteByProduct(productModel);
	}

	public List<ProductSizeModel> updateSizesOfProduct(List<SizeModel> persistentProductSizeModels, List<SizeModel> requestSizeModels, ProductModel productModel) {
		requestSizeModels.stream()
				.filter(sizeModel -> !persistentProductSizeModels.contains(sizeModel))
				.forEach(sizeModel ->  this.productSizeRepository.save(new ProductSizeModel(productModel, sizeModel)));

		persistentProductSizeModels.stream()
				.filter(sizeModel -> !requestSizeModels.contains(sizeModel))
				.forEach(sizeModel -> this.productSizeRepository.deleteById(new ProductSizeId(productModel.getProductId(), sizeModel.getSizeId())));

		return this.getProductSizesByProduct(productModel);
	}

}
