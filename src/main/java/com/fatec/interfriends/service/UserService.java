package com.fatec.interfriends.service;

import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    UserResponseDto deleteUser(Long id);

}
