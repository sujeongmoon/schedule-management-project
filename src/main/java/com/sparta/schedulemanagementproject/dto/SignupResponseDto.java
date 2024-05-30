package com.sparta.schedulemanagementproject.dto;

import com.sparta.schedulemanagementproject.entity.User;
import com.sparta.schedulemanagementproject.entity.UserRoleEnum;

import lombok.Getter;

@Getter
public class SignupResponseDto {

	private Long userId;
	private String userNickname;
	private String userName;
	private UserRoleEnum userRole;


	public SignupResponseDto(User user){
		this.userId = user.getUserId();
		this.userNickname = user.getUserNickname();
		this.userName = user.getUserName();
		this.userRole = user.getUserRole();
	}
}
