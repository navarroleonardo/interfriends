package com.fatec.interfriends.domain.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    @NotNull
    private String number;
    @NotNull
    private Integer expirationMonth;
    @NotNull
    private Integer expirationYear;
    @NotNull
    private String securityCode;
    private Boolean store = false;

}
