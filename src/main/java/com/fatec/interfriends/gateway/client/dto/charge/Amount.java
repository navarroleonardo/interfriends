package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatec.interfriends.gateway.client.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amount {

    @JsonProperty(value = "value")
    Integer value;

    @JsonProperty(value = "currency")
    CurrencyEnum currency = CurrencyEnum.BRL;

    public Amount(Integer value) {
        this.value = value;
    }

}
