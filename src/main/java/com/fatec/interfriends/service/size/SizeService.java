package com.fatec.interfriends.service.size;

import java.util.List;

import com.fatec.interfriends.domain.dto.size.SizeRequestDto;
import com.fatec.interfriends.domain.model.ProductSizeModel;
import com.fatec.interfriends.domain.model.SizeModel;

public interface SizeService {

    SizeModel createSize(SizeRequestDto sizeRequestDto);
    SizeModel getSize(Long id);
    List<SizeModel> getSizesById(List<Long> sizeIds);
    List<SizeModel> getSizesByProductSizes(List<ProductSizeModel> productSizeModels);
    SizeModel updateSize(Long id, SizeRequestDto sizeRequestDto);
    SizeModel deleteSize(Long id);

}
