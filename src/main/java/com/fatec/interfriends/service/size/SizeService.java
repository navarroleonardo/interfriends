package com.fatec.interfriends.service.size;

import java.util.List;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;

public interface SizeService {

    SizeResponseDto createSize(SizeRequestDto sizeRequestDto);
    SizeResponseDto getSize(Long id);
    List<SizeModel> getSizes(List<Long> sizeIds);
    List<SizeModel> getSizesByProductSizes(List<ProductSizeModel> productSizeModels);
    SizeResponseDto updateSize(Long id, SizeRequestDto sizeRequestDto);
    SizeResponseDto deleteSize(Long id);

}
