package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatec.interfriends.gateway.client.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    @JsonProperty(value = "type")
    private PaymentTypeEnum type;

    @JsonProperty(value = "card")
    private Card card;

    @JsonProperty(value = "boleto")
    private Boleto boleto;

    @JsonProperty(value = "authentication_method")
    private AuthenticationMethod authenticationMethod;

    @JsonProperty(value = "installments")
    private Integer installments;

    @JsonProperty(value = "capture")
    private Boolean capture;

    @JsonProperty(value = "soft_descriptor")
    private String softDescriptor = "RomaniStore";

}
