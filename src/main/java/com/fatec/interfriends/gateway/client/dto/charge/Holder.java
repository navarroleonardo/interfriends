package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holder {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "tax_id")
    private String taxId;

    @JsonProperty(value = "email")
    private String email;

    // TODO: VALIDAR NECESSIDADE DE ENDEREÇO NO BOLETO
    // @JsonProperty(value = "address")
    // private Address address;

    public Holder(String name) {
        this.name = name;
    }
}
