package com.fatec.interfriends.domain.dto.user;

import com.fatec.interfriends.domain.dto.address.AddressResponseDto;
import com.fatec.interfriends.domain.dto.phone.PhoneResponseDto;
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
    private List<AddressResponseDto> addresses = new ArrayList<>();
    private List<PhoneResponseDto> phones = new ArrayList<>();

    public UserResponseDto(UserModel userModel) {
        BeanUtils.copyProperties(userModel, this);
        userModel.getAddresses().forEach(addressModel -> this.addresses.add(new AddressResponseDto(addressModel)));
        userModel.getPhones().forEach(phoneModel -> this.phones.add(new PhoneResponseDto(phoneModel)));
    }

}
