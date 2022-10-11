package com.fatec.interfriends.domain.dto.phone;

import com.fatec.interfriends.domain.model.Phone;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PhoneResponseDto {

    private Long phoneId;
    private Long userId;
    private String ddi;
    private String ddd;
    private String number;

    public PhoneResponseDto(Phone phone) {
        BeanUtils.copyProperties(phone, this);
        this.userId = phone.getUser().getUserId();
    }
}
