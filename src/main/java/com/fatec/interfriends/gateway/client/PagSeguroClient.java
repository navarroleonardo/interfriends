package com.fatec.interfriends.gateway.client;

import com.fatec.interfriends.gateway.client.dto.charge.CreateChargeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pag-seguro", url = "https://sandbox.api.pagseguro.com")
public interface PagSeguroClient {

    @PostMapping(value = "charges", consumes = "application/json", produces = "application/json")
    ResponseEntity<Object> createCharges(
            @RequestHeader(value = "Authorization") String authorization,
            // TODO: VALIDAR NECESSIDADE DE IDEMPOTENCY KEY NOS HEADERS
            //@RequestHeader(value = "x-idempotency-key") String idempotencyKey,
            @RequestBody CreateChargeRequestDto createChargeRequestDto
    );
}
