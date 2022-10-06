package com.fatec.interfriends.service.inventory;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.dto.inventory.InventoryResponseDto;
import com.fatec.interfriends.domain.model.ProductSizeId;
import com.fatec.interfriends.domain.model.ProductSizeModel;
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
    public InventoryResponseDto addProductsToInventory(InventoryRequestDto inventoryRequestDto) {
        ProductSizeId productSizeId = new ProductSizeId(inventoryRequestDto.getProductId(), inventoryRequestDto.getSizeId());
        Optional<ProductSizeModel> productSizeModelOptional = this.productSizeRepository.findById(productSizeId);

        if (productSizeModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O tamanho selecionado não está disponível no produto selecionado.");
        }

        ProductSizeModel productSizeModel = productSizeModelOptional.get();

        productSizeModel.setQuantity(productSizeModel.getQuantity() + inventoryRequestDto.getQuantity());

        productSizeModel = this.productSizeRepository.save(productSizeModel);

        return new InventoryResponseDto(productSizeModel);
    }

    @Override
    public InventoryResponseDto removeProductsFromInventory(InventoryRequestDto inventoryRequestDto) {
        ProductSizeId productSizeId = new ProductSizeId(inventoryRequestDto.getProductId(), inventoryRequestDto.getSizeId());
        Optional<ProductSizeModel> productSizeModelOptional = this.productSizeRepository.findById(productSizeId);

        if (productSizeModelOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O tamanho selecionado não está disponível no produto selecionado.");
        }

        ProductSizeModel productSizeModel = productSizeModelOptional.get();

        productSizeModel.setQuantity(productSizeModel.getQuantity() - inventoryRequestDto.getQuantity());

        productSizeModel = this.productSizeRepository.save(productSizeModel);

        return new InventoryResponseDto(productSizeModel);
    }

}
