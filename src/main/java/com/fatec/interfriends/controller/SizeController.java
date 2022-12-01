package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.service.size.SizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new SizeResponseDto(this.sizeService.createSize(sizeRequestDto)));
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<SizeResponseDto> getSize(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new SizeResponseDto(this.sizeService.getSize(id)));
    }
    
    @GetMapping()
    public ResponseEntity<List<SizeResponseDto>> getSizes() {
        return ResponseEntity.status(HttpStatus.OK).body(SizeResponseDto.SizesResponseDto(this.sizeService.getAllSizes()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SizeResponseDto> alterSize(@PathVariable(value = "id") Long id, @RequestBody @Valid SizeRequestDto sizeRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new SizeResponseDto(this.sizeService.updateSize(id, sizeRequestDto)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SizeResponseDto> deleteSize(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new SizeResponseDto(this.sizeService.deleteSize(id)));
    }

}
