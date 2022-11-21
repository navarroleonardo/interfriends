package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;
import com.fatec.interfriends.repository.ProductSizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

	public List<ProductSize> bindSizesToProduct(Product product, List<Size> sizes) {
		return sizes.stream()
				.map(size -> this.productSizeRepository.save(new ProductSize(product, size)))
				.toList();
	}
	
	public List<ProductSize> bindSizeToProduct(Product product, Size size, Long quantity) {
		List<ProductSize> productSizes = new ArrayList<ProductSize>();
		productSizes.add(this.productSizeRepository.save(new ProductSize(product, size, quantity)));
		return productSizes;
	}

	@Override
	public List<ProductSize> getProductSizesByProduct(Product product) {
		return this.productSizeRepository.findAllByProduct(product);
	}

	@Override
	public void deleteByProduct(Product product) {
		this.productSizeRepository.deleteByProduct(product);
	}

	public List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, List<Size> requestSizes, Product product) {
		requestSizes.stream()
				.filter(size -> !persistentProductSizes.contains(size))
				.forEach(size ->  this.productSizeRepository.save(new ProductSize(product, size)));

		persistentProductSizes.stream()
				.filter(size -> !requestSizes.contains(size))
				.forEach(size -> this.productSizeRepository.deleteById(new ProductSizeId(product.getProductId(), size.getSizeId())));

		return this.getProductSizesByProduct(product);
	}

	@Override
	public ProductSize getProductSize(ProductSizeId productSizeId) {
		Optional<ProductSize> optionalProductSize = this.productSizeRepository.findById(productSizeId);

		if (optionalProductSize.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado no tamanho selecionado.");
		}

		return optionalProductSize.get();
	}

	@Override
	public List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, Size requestSizes,
			Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
