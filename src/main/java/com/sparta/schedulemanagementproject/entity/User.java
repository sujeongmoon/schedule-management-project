package com.sparta.schedulemanagementproject.entity;

import com.sparta.schedulemanagementproject.dto.LoginRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable = false)
	private String userNickname;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String userPassword;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum userRole;

	public User(String username, String userNickname, String password, UserRoleEnum role) {
		this.userName = username;
		this.userNickname = userNickname;
		this.userPassword = password;
		this.userRole = role;
	}

	public boolean checkPassword(LoginRequestDto requestDto) {
		return this.getUserPassword().equals(requestDto.getUserPassword());
	}

}