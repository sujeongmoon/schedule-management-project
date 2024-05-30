package com.sparta.schedulemanagementproject.service;


import com.sparta.schedulemanagementproject.dto.LoginRequestDto;
import com.sparta.schedulemanagementproject.dto.LoginResponseDto;
import com.sparta.schedulemanagementproject.dto.SignupRequestDto;
import com.sparta.schedulemanagementproject.dto.SignupResponseDto;
import com.sparta.schedulemanagementproject.entity.User;
import com.sparta.schedulemanagementproject.entity.UserRoleEnum;
import com.sparta.schedulemanagementproject.jwt.JwtUtil;
import com.sparta.schedulemanagementproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

	public SignupResponseDto signup( SignupRequestDto requestDto) {
		String userName = requestDto.getUserName();
		String userPassword = requestDto.getUserPassword();
		String userNickname = requestDto.getUserNickname();

		Optional<User> checkUsername = userRepository.findByUserName(userName);
		if (checkUsername.isPresent()) {
			throw new IllegalArgumentException("중복된 username입니다.");
		}

		UserRoleEnum role = UserRoleEnum.USER;
		if (requestDto.isAdmin()) {
			if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
				throw new IllegalArgumentException("관리자 암호가 틀려 관리자 등록이 불가능합니다.");
			}
			role = UserRoleEnum.ADMIN;
		}

		User user = new User(userName, userNickname, userPassword, role);
		userRepository.save(user);

		SignupResponseDto signupResponseDto = new SignupResponseDto(user);
		return signupResponseDto;
	}

	public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse res) {

		String userName = requestDto.getUserName();

		User user = userRepository.findByUserName(userName).orElseThrow(
			() -> new IllegalArgumentException("회원을 찾을 수 없습니다")
		);

		if (!user.checkPassword(requestDto)) {
			throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
		}

		String token = jwtUtil.createToken(user.getUserName(), user.getUserRole());
		jwtUtil.addJwtToCookie(token, res);

		LoginResponseDto loginResponseDto = new LoginResponseDto(user, token);
		return loginResponseDto;

	}

}



