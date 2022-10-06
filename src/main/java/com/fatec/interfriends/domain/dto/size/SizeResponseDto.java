package com.fatec.interfriends.domain.dto.size;

import com.fatec.interfriends.domain.model.SizeModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SizeResponseDto {

    private Long sizeId;
    private String size;
    private String description;

    public SizeResponseDto(SizeModel sizeModel) {
        BeanUtils.copyProperties(sizeModel, this);
    }

}
