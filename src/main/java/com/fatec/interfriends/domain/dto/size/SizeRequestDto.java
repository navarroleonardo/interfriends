package com.fatec.interfriends.domain.dto.size;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SizeRequestDto {

    @NotNull
    private String size;
    private String description;

}
