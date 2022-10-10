package com.fatec.interfriends.domain.dto.address;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private String publicLocal;
    @NotNull
    private Integer number;
    private String complement;

}
