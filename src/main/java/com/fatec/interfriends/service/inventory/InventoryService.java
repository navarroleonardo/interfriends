package com.fatec.interfriends.service.inventory;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.model.ProductSize;

public interface InventoryService {

    ProductSize addProductsToInventory(InventoryRequestDto inventoryRequestDto);
    ProductSize removeProductsFromInventory(InventoryRequestDto inventoryRequestDto);

}
