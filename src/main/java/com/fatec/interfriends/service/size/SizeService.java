package com.fatec.interfriends.service.size;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;

public interface SizeService {

    SizeResponseDto createSize(SizeRequestDto sizeRequestDto);
    SizeResponseDto getSize(Long id);
    SizeResponseDto updateSize(Long id, SizeRequestDto sizeRequestDto);
    SizeResponseDto deleteSize(Long id);

}
