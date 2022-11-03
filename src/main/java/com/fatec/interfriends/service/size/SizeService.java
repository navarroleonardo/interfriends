package com.fatec.interfriends.service.size;

import java.util.List;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.model.ProductSize;
import com.fatec.interfriends.domain.model.Size;

public interface SizeService {

    Size createSize(SizeRequestDto sizeRequestDto);
    Size getSize(Long id);
    List<Size> getSizesById(List<Long> sizeIds);
    List<Size> getSizesByProductSizes(List<ProductSize> productSizes);
    List<Size> getAllSizes();
    Size updateSize(Long id, SizeRequestDto sizeRequestDto);
    Size deleteSize(Long id);

}
