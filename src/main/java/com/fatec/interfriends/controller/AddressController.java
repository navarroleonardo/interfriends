package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import com.fatec.interfriends.domain.dto.address.AddressResponseDto;
import com.fatec.interfriends.domain.model.Address;
import com.fatec.interfriends.service.address.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Transactional
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody @Valid AddressRequestDto addressRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddressResponseDto(this.addressService.createAddress(addressRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{addressId}")
    @Transactional
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable(value = "addressId") Long addressId) {
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDto(this.addressService.getAddress(addressId)));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{userId}")
    @Transactional
    public ResponseEntity<Page<Address>> getAddressByUser(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.addressService.getAddressByUser(userId, pageable));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{addressId}")
    @Transactional
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable(value = "addressId") Long addressId, @RequestBody @Valid AddressRequestDto addressRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDto(this.addressService.updateAddress(addressId, addressRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{addressId}")
    @Transactional
    public ResponseEntity<AddressResponseDto> deleteAddress(@PathVariable(value = "addressId") Long addressId) {
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDto(this.addressService.deleteAddress(addressId)));
    }

}
