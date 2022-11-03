package com.fatec.interfriends.domain.dto.size;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SizeRequestDto {

    @NotNull
    private String name;
    private String description;

}
