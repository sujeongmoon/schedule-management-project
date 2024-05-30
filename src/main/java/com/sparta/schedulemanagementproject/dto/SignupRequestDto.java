package com.sparta.schedulemanagementproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
	@NotBlank
	private String userNickname;
	@Size(min=4,max=10, message = "username은 최소 4자 이상, 10자 이하여야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "username은 알파벳 소문자, 숫자로 구성돼야합니다.")
	private String userName;
	@Size(min=8, max=15)
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "userPassword는 알파벳 대소문자, 숫자로 구성돼야합니다.")
	private String userPassword;

	private boolean admin = false;
	private String adminToken = "";
}
