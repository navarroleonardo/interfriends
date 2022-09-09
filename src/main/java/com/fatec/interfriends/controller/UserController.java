package com.fatec.interfriends.controller;

import com.fatec.interfriends.domain.dto.user.UserResponseDto;
import com.fatec.interfriends.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.interfriends.domain.dto.user.UserRequestDto;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController (UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userRequestDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUser(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> alterUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRequestDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponseDto> deleteUser(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(id));
	}

}
