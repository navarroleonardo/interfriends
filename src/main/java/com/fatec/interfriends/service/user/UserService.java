package com.fatec.interfriends.service.user;

import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.login.LoginResponseDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.dto.user.UserResponseDto;

public interface UserService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    UserResponseDto createUser(UserRequestDto userRequestDto, Boolean isAdmin);
    UserResponseDto getUser(Long id);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    UserResponseDto deleteUser(Long id);

}
