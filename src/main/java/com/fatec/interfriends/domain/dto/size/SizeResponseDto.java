package com.fatec.interfriends.domain.dto.size;

import com.fatec.interfriends.domain.model.Size;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

@Data
public class SizeResponseDto {

    private Long sizeId;
    private String name;
    private String description;

    public SizeResponseDto(Size size) {
        BeanUtils.copyProperties(size, this);
    }
    
    public static List<SizeResponseDto> SizesResponseDto(List<Size> sizes){
    	return sizes.stream().map(SizeResponseDto::new).collect(Collectors.toList());
    }

}
