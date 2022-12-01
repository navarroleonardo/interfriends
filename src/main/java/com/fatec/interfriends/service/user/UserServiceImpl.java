package com.fatec.interfriends.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fatec.interfriends.config.security.TokenProvider;
import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.model.Role;
import com.fatec.interfriends.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.interfriends.domain.model.User;
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
	public String login(LoginRequestDto loginRequestDto) {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(),
						loginRequestDto.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return tokenProvider.generateToken(authentication);
	}

	@Override
	public User getUser (Long id){
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		return optionalUser.get();
	}

	@Override
	public User createUser(UserRequestDto userRequestDto, Boolean isAdmin) {
		User newUser = new User(userRequestDto);

		Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());

		if (optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail informado já pertence a um usuário.");
		}

		addRoles(newUser, isAdmin);
		encryptPassword(newUser);

		return userRepository.save(newUser);
	}

	@Override
	public User updateUser (Long id, UserRequestDto userRequestDto) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		if (userRequestDto.getEmail() == null || userRequestDto.getName() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os campos nome e email são obrigatórios");
		}

		User user = optionalUser.get(); 
		user.setName(userRequestDto.getName());
		user.setEmail(userRequestDto.getEmail());
		if (userRequestDto.getPassword() != null) {
			user.setPassword(userRequestDto.getPassword());
			encryptPassword(user);
		}
		user.setBirthdate(userRequestDto.getBirthdate());

		return userRepository.save(user);
	}

	@Override
	public User deleteUser (Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		userRepository.delete(optionalUser.get());

		return optionalUser.get();
	}

	private void encryptPassword (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	private void addRoles (User user, Boolean isAdmin) {
		List<Role> roles = new ArrayList<>();

		Optional<Role> userRole = roleRepository.findRoleByRoleName("ROLE_USER");
		//RoleName.ROLE_USER.toString()

		if (userRole.isEmpty()) {
			throw new RuntimeException("");
		}

		roles.add(userRole.get());

		if (isAdmin) {
			Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_ADMIN");
			//RoleName.ROLE_ADMIN.toString()

			if (adminRole.isEmpty()) {
				throw new RuntimeException("");
			}

			roles.add(adminRole.get());
		}

		user.setRoles(roles);
	}

	@Override
	public User findByCpf(String cpf) {
		Optional<User> optionalUser = userRepository.findByCpf(cpf);

		if (optionalUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
		}

		return optionalUser.get();
	}
	
}
