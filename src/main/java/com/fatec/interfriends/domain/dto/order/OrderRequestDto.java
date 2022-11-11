package com.fatec.interfriends.domain.dto.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long addressId;
    @NotNull
    private List<OrderProductRequestDto> orderProducts;
    private UUID couponId;

}
