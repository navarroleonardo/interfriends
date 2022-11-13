package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @JsonProperty(value = "street")
    private String street;

    @JsonProperty(value = "number")
    private String number;

    @JsonProperty(value = "complement")
    private String complement;

    @JsonProperty(value = "locality")
    private String locality;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "region_code")
    private String regionCode;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "postal_code")
    private String postalCode;

}
