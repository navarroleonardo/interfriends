package com.fatec.interfriends.domain.dto.phone;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PhoneRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private String ddi;
    private String ddd;
    @NotNull
    private String number;

}
