package com.fatec.interfriends.service.product;

import com.fatec.interfriends.domain.dto.product.ProductSizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeQuantity;
import com.fatec.interfriends.domain.model.Product;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;
import com.fatec.interfriends.repository.ProductSizeRepository;
import com.fatec.interfriends.service.size.SizeServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;
    private final SizeServiceImpl sizeService;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository, SizeServiceImpl sizeService) {
        this.productSizeRepository = productSizeRepository;
        this.sizeService = sizeService;
    }
    
    
    
	public List<ProductSize> bindSizesToProduct(Product product,  List<ProductSizeRequestDto> productSizes) {
		List<SizeQuantity> sizesQuantity =  productSizes.stream()
					.map(productSize -> new SizeQuantity(this.sizeService.getSize(productSize.getSizeId()),  productSize.getQuantity()))
					.toList();
		System.out.println(sizesQuantity.get(0).getSize());
		System.out.println(sizesQuantity.get(0).getQuantity());
		return sizesQuantity.stream()
				.map(size -> 
					this.productSizeRepository.save(new ProductSize(product, size.getSize(), size.getQuantity()))
				)
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
	public List<ProductSize> updateSizesOfProduct(List<SizeQuantity> persistentProductSizes, Product product) {
		List<ProductSize> productSizes = persistentProductSizes.stream()
							  .map(size ->  this.productSizeRepository.save(new ProductSize(product, size.getSize(), size.getQuantity()))).toList();
		return productSizes;
	}



	@Override
	public List<ProductSize> updateSizesOfProduct(List<Size> persistentProductSizes, Size requestSizes,
			Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
