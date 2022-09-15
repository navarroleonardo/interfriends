package com.fatec.interfriends.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fatec.interfriends.config.security.TokenProvider;
import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.login.LoginResponseDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.dto.user.UserResponseDto;
import com.fatec.interfriends.domain.enums.RoleName;
import com.fatec.interfriends.domain.model.RoleModel;
import com.fatec.interfriends.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.interfriends.domain.model.UserModel;
import com.fatec.interfriends.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(),
						loginRequestDto.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = tokenProvider.generateToken(authentication);

		return new LoginResponseDto(token);

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
	public UserResponseDto createUser(UserRequestDto userRequestDto, Boolean isAdmin) {
		var newUser = new UserModel(userRequestDto);

		Optional<UserModel> existingUser = userRepository.findByEmail(newUser.getEmail());

		if (existingUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já pertence a um usuário.");
		}

		addRoles(newUser, isAdmin);
		encryptPassword(newUser);

		return new UserResponseDto(userRepository.save(newUser));
	}

	@Override
	public UserResponseDto updateUser (Long id, UserRequestDto userRequestDto) {
		Optional<UserModel> userModelOptional = userRepository.findById(id);

		if (!userModelOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		UserModel userModel = new UserModel(userRequestDto);

		userModel.setUserId(userModelOptional.get().getUserId());

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

	private void encryptPassword (UserModel userModel) {
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
	}

	private void addRoles (UserModel userModel, Boolean isAdmin) {
		List<RoleModel> roles = new ArrayList<>();

		Optional<RoleModel> userRole = roleRepository.findRoleByRoleName(RoleName.ROLE_USER.toString());

		if (userRole.isEmpty()) {
			throw new RuntimeException("");
		}

		roles.add(userRole.get());

		if (isAdmin) {
			Optional<RoleModel> adminRole = roleRepository.findRoleByRoleName(RoleName.ROLE_ADMIN.toString());

			if (adminRole.isEmpty()) {
				throw new RuntimeException("");
			}

			roles.add(adminRole.get());
		}

		userModel.setRoles(roles);
	}
	
}
