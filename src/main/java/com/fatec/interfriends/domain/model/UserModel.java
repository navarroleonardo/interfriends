package com.fatec.interfriends.domain.model;

import javax.persistence.*;

import com.fatec.interfriends.domain.dto.user.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	@Column
	private String birthdate;
	@Column
	private String phone;

	public UserModel (UserRequestDto userRequestDto) {
		BeanUtils.copyProperties(userRequestDto, this);
	}

}
