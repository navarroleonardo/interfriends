package com.fatec.interfriends.domain.dto.payment;

import com.fatec.interfriends.gateway.client.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull
    PaymentTypeEnum type;
    Integer installments;
    CardRequestDto card;
    BoletoRequestDto boleto;

}
