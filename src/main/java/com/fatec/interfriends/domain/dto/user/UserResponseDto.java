package com.fatec.interfriends.domain.dto.user;

import com.fatec.interfriends.domain.model.UserModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String birthdate;
    private String phone;

    public UserResponseDto(UserModel userModel) {
        BeanUtils.copyProperties(userModel, this);
    }

}
