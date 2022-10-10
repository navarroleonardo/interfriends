package com.fatec.interfriends.domain.dto.phone;

import com.fatec.interfriends.domain.model.PhoneModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PhoneResponseDto {

    private Long phoneId;
    private Long userId;
    private String ddi;
    private String ddd;
    private String number;

    public PhoneResponseDto(PhoneModel phoneModel) {
        BeanUtils.copyProperties(phoneModel, this);
        this.userId = phoneModel.getUser().getUserId();
    }
}
