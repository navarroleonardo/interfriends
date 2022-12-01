package com.fatec.interfriends.service.user;

import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.model.User;

public interface UserService {

    String login(LoginRequestDto loginRequestDto);
    User createUser(UserRequestDto userRequestDto, Boolean isAdmin);
    User getUser(Long id);
    User updateUser(Long id, UserRequestDto userRequestDto);
    User deleteUser(Long id);
    User findByCpf(String cpf);

}
