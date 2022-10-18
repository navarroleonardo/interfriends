package com.fatec.interfriends.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class OrderProductId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long productId;
    private Long sizeId;

    public OrderProductId(Long orderId, Long productId, Long sizeId) {
        super();
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
    }
}
