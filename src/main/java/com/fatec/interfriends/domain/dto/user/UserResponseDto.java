package com.fatec.interfriends.domain.dto.user;

import com.fatec.interfriends.domain.dto.address.AddressResponseDto;
import com.fatec.interfriends.domain.model.UserModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String birthdate;
    private String phone;
    private List<AddressResponseDto> addresses = new ArrayList<>();

    public UserResponseDto(UserModel userModel) {
        BeanUtils.copyProperties(userModel, this);
        userModel.getAddresses().forEach(addressModel -> this.addresses.add(new AddressResponseDto(addressModel)));
    }

}
