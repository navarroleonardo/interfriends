package com.fatec.interfriends.service.inventory;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.model.ProductSizeModel;

public interface InventoryService {

    ProductSizeModel addProductsToInventory(InventoryRequestDto inventoryRequestDto);
    ProductSizeModel removeProductsFromInventory(InventoryRequestDto inventoryRequestDto);

}
