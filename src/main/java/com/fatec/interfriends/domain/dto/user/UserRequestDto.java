package com.fatec.interfriends.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {

	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String birthdate;
	@NotNull
	private String cpf; 
	

}
