package com.fatec.interfriends.service.user;

import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.model.UserModel;

public interface UserService {

    String login(LoginRequestDto loginRequestDto);
    UserModel createUser(UserRequestDto userRequestDto, Boolean isAdmin);
    UserModel getUser(Long id);
    UserModel updateUser(Long id, UserRequestDto userRequestDto);
    UserModel deleteUser(Long id);

}
