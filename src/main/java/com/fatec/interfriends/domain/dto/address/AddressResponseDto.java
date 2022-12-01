package com.fatec.interfriends.domain.dto.address;

import com.fatec.interfriends.domain.model.Address;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AddressResponseDto {

    private Long addressId;
    private Long userId;
    private String publicLocal;
    private Integer number;
    private String complement;
    private String cep;

    public AddressResponseDto(Address address) {
        BeanUtils.copyProperties(address, this);
        this.userId = address.getUser().getUserId();
    }

}
