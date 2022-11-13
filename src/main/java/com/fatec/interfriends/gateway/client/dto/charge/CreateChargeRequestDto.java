package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChargeRequestDto {

    @JsonProperty(value = "reference_id")
    private String referenceId;

    @JsonProperty(value = "amount")
    private Amount amount;

    @JsonProperty(value = "payment_method")
    private PaymentMethod paymentMethod;

    // TODO: VERIFICAR NECESSIDADE DAS NOTIFICATION URLS
    // @JsonProperty(value = "notification_urls")
    // private String notificationUrls;

}
