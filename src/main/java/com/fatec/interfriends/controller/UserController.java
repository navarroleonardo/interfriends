package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.login.LoginRequestDto;
import com.fatec.interfriends.domain.dto.login.LoginResponseDto;
import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import com.fatec.interfriends.domain.dto.user.UserResponseDto;
import com.fatec.interfriends.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController (UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
		return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(this.userService.login(loginRequestDto)));
	}

	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(this.userService.createUser(userRequestDto, Boolean.FALSE)));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/admin")
	public ResponseEntity<UserResponseDto> createAdmin(@RequestBody @Valid UserRequestDto userRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(this.userService.createUser(userRequestDto, Boolean.TRUE)));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(this.userService.getUser(id)));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> alterUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(this.userService.updateUser(id, userRequestDto)));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponseDto> deleteUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(this.userService.deleteUser(id)));
	}

}
