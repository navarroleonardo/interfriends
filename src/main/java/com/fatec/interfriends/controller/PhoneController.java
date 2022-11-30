package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
import com.fatec.interfriends.domain.dto.phone.PhoneResponseDto;
import com.fatec.interfriends.domain.model.Phone;
import com.fatec.interfriends.service.phone.PhoneService;
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
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Transactional
    public ResponseEntity<PhoneResponseDto> createPhone(@RequestBody @Valid PhoneRequestDto phoneRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new PhoneResponseDto(this.phoneService.createPhone(phoneRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{phoneId}")
    @Transactional
    public ResponseEntity<PhoneResponseDto> getPhone(@PathVariable(value = "phoneId") Long phoneId) {
        return ResponseEntity.status(HttpStatus.OK).body(new PhoneResponseDto(this.phoneService.getPhone(phoneId)));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{userId}")
    @Transactional
    public ResponseEntity<Page<Phone>> getPhoneByUser(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.phoneService.getPhoneByUser(userId, pageable));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{phoneId}")
    @Transactional
    public ResponseEntity<PhoneResponseDto> updatePhone(@PathVariable(value = "phoneId") Long phoneId, @RequestBody @Valid PhoneRequestDto phoneRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new PhoneResponseDto(this.phoneService.updatePhone(phoneId, phoneRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{phoneId}")
    @Transactional
    public ResponseEntity<PhoneResponseDto> deletePhone(@PathVariable(value = "phoneId") Long phoneId) {
        return ResponseEntity.status(HttpStatus.OK).body(new PhoneResponseDto(this.phoneService.deletePhone(phoneId)));
    }

}
