package com.fatec.interfriends.domain.dto.user;

import com.fatec.interfriends.domain.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BasicUserResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String birthdate;

    public BasicUserResponseDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
