package com.fatec.interfriends.domain.dto.address;

import com.fatec.interfriends.domain.model.AddressModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AddressResponseDto {

    private Long addressId;
    private Long userId;
    private String publicLocal;
    private Integer number;
    private String complement;

    public AddressResponseDto(AddressModel addressModel) {
        BeanUtils.copyProperties(addressModel, this);
        this.userId = addressModel.getUser().getUserId();
    }

}
