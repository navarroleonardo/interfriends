package com.fatec.interfriends.domain.dto.order;

import com.fatec.interfriends.domain.dto.product.BasicProductResponseDto;
import com.fatec.interfriends.domain.dto.size.SizeResponseDto;
import com.fatec.interfriends.domain.model.OrderProduct;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class OrderProductResponseDto {

    private BasicProductResponseDto product;
    private SizeResponseDto size;
    private Long quantity;
    private Double price;

    public OrderProductResponseDto(OrderProduct orderProduct) {
        BeanUtils.copyProperties(orderProduct, this);
        this.product = new BasicProductResponseDto(orderProduct.getProduct());
        this.size = new SizeResponseDto(orderProduct.getSize());
    }

}
