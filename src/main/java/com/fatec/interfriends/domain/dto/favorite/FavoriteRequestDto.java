package com.fatec.interfriends.domain.dto.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FavoriteRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long productId;

}
