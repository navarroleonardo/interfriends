package com.fatec.interfriends.domain.dto.user;

import com.fatec.interfriends.domain.dto.address.AddressResponseDto;
import com.fatec.interfriends.domain.dto.phone.PhoneResponseDto;
import com.fatec.interfriends.domain.model.User;
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

    public UserResponseDto(User user) {
        BeanUtils.copyProperties(user, this);
        user.getAddresses().forEach(address -> this.addresses.add(new AddressResponseDto(address)));
        user.getPhones().forEach(phone -> this.phones.add(new PhoneResponseDto(phone)));
    }

}
