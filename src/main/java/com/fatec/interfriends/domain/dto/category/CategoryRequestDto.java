package com.fatec.interfriends.domain.dto.category;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryRequestDto {

    @NotNull
    private String name;

}
