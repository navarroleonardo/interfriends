package com.fatec.interfriends.service;

import java.util.Optional;

import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.dto.user.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fatec.interfriends.domain.model.UserModel;
import com.fatec.interfriends.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponseDto getUser (Long id){
		Optional<UserModel> userModelOptional = userRepository.findById(id);

		if (!userModelOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		return new UserResponseDto(userModelOptional.get());
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		var newUser = new UserModel(userRequestDto);

		Optional<UserModel> existingUser = userRepository.findByEmail(newUser.getEmail());

		if (existingUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já pertence a um usuário.");
		}

		return new UserResponseDto(userRepository.save(newUser));
	}

	@Override
	public UserResponseDto updateUser (Long id, UserRequestDto userRequestDto) {
		Optional<UserModel> userModelOptional = userRepository.findById(id);

		if (!userModelOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		UserModel userModel = new UserModel(userRequestDto);

		userModel.setId(userModelOptional.get().getId());

		return new UserResponseDto(userRepository.save(userModel));
	}

	@Override
	public UserResponseDto deleteUser (Long id) {
		Optional<UserModel> userModelOptional = userRepository.findById(id);

		if (!userModelOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		userRepository.delete(userModelOptional.get());

		return new UserResponseDto(userModelOptional.get());
	}
	
}
