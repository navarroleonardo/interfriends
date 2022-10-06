package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.repository.SizeRepository;
import com.fatec.interfriends.service.size.SizeService;
import com.fatec.interfriends.service.size.SizeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/size")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SizeResponseDto> createSize(@RequestBody @Valid SizeRequestDto sizeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sizeService.createSize(sizeRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SizeResponseDto> getSize(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.sizeService.getSize(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SizeResponseDto> alterSize(@PathVariable(value = "id") Long id, @RequestBody @Valid SizeRequestDto sizeRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.sizeService.updateSize(id, sizeRequestDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SizeResponseDto> deleteSize(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.sizeService.deleteSize(id));
    }

}
