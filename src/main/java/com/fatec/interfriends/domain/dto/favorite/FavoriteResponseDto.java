package com.fatec.interfriends.domain.dto.favorite;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class FavoriteResponseDto {

    private Long userId;
    private Long productId;

    public FavoriteResponseDto(FavoriteRequestDto favoriteRequestDto) {
        BeanUtils.copyProperties(favoriteRequestDto, this);
    }
}
