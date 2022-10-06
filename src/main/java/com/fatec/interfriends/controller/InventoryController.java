package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.inventory.InventoryRequestDto;
import com.fatec.interfriends.domain.dto.inventory.InventoryResponseDto;
import com.fatec.interfriends.service.inventory.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController (InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("add")
    @Transactional
    public ResponseEntity<InventoryResponseDto> addProductsToInventory(@RequestBody @Valid InventoryRequestDto inventoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.inventoryService.addProductsToInventory(inventoryRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("remove")
    @Transactional
    public ResponseEntity<InventoryResponseDto> removeProductsFromInventory(@RequestBody @Valid InventoryRequestDto inventoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.inventoryService.removeProductsFromInventory(inventoryRequestDto));
    }


}
