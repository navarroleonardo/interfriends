package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatec.interfriends.domain.dto.payment.CardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @JsonProperty(value = "number")
    private String number;

    @JsonProperty(value = "exp_month")
    private Integer expirationMonth;

    @JsonProperty(value = "exp_year")
    private Integer expirationYear;

    @JsonProperty(value = "security_code")
    private String securityCode;

    @JsonProperty(value = "store")
    private Boolean store = false;

    @JsonProperty(value = "holder")
    private Holder holder;

    public Card(CardRequestDto cardRequestDto, Holder holder) {
        BeanUtils.copyProperties(cardRequestDto, this);
        this.holder = holder;
    }

}
