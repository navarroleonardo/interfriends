package com.fatec.interfriends.service.inventory;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.dto.inventory.InventoryResponseDto;

public interface InventoryService {

    InventoryResponseDto addProductsToInventory(InventoryRequestDto inventoryRequestDto);
    InventoryResponseDto removeProductsFromInventory(InventoryRequestDto inventoryRequestDto);

}
