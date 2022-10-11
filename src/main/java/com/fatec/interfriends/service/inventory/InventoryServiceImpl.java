package com.fatec.interfriends.service.inventory;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.repository.ProductSizeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final ProductSizeRepository productSizeRepository;

    public InventoryServiceImpl (ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public ProductSize addProductsToInventory(InventoryRequestDto inventoryRequestDto) {
        ProductSizeId productSizeId = new ProductSizeId(inventoryRequestDto.getProductId(), inventoryRequestDto.getSizeId());
        Optional<ProductSize> optionalProductSize = this.productSizeRepository.findById(productSizeId);

        if (optionalProductSize.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O tamanho selecionado não está disponível no produto selecionado.");
        }

        ProductSize productSize = optionalProductSize.get();

        productSize.setQuantity(productSize.getQuantity() + inventoryRequestDto.getQuantity());

        return this.productSizeRepository.save(productSize);
    }

    @Override
    public ProductSize removeProductsFromInventory(InventoryRequestDto inventoryRequestDto) {
        ProductSizeId productSizeId = new ProductSizeId(inventoryRequestDto.getProductId(), inventoryRequestDto.getSizeId());
        Optional<ProductSize> optionalProductSize = this.productSizeRepository.findById(productSizeId);

        if (optionalProductSize.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O tamanho selecionado não está disponível no produto selecionado.");
        }

        ProductSize productSize = optionalProductSize.get();

        productSize.setQuantity(productSize.getQuantity() - inventoryRequestDto.getQuantity());

        return this.productSizeRepository.save(productSize);
    }

}
