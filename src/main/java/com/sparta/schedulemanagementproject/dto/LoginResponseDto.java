package com.sparta.schedulemanagementproject.dto;

import java.time.LocalDateTime;

import com.sparta.schedulemanagementproject.entity.User;
import com.sparta.schedulemanagementproject.entity.UserRoleEnum;

import lombok.Getter;

@Getter
public class LoginResponseDto {
	private Long userId;
	private String userName;
	private String userNickname;
	private UserRoleEnum userRole;
	private LocalDateTime createdAt;
	private String token;

	public LoginResponseDto(User user, String token) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.userNickname = user.getUserNickname();
		this.userRole = user.getUserRole();
		this.createdAt = user.getCreatedAt();
		this.token = token;
	}
}
