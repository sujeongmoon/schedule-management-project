package com.sparta.schedulemanagementproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedulemanagementproject.CommonResponse;
import com.sparta.schedulemanagementproject.dto.LoginRequestDto;
import com.sparta.schedulemanagementproject.dto.LoginResponseDto;
import com.sparta.schedulemanagementproject.dto.SignupRequestDto;
import com.sparta.schedulemanagementproject.dto.SignupResponseDto;
import com.sparta.schedulemanagementproject.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	// 6단계 : 회원가입
	// POST http://localhost:8080/api/users/signup
	// {"userNickname":"유저", "userName":"user11111", "userPassword":"userPassword"}
	// {"userNickname":"관리자", "userName":"user1111", "userPassword":"userPassword", "admin": true, "adminToken":"AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC"}
	@PostMapping("/users/signup")
	public ResponseEntity<CommonResponse<SignupResponseDto>> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult){

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if(fieldErrors.size() > 0) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
			}
			return ResponseEntity.badRequest()
				.body(CommonResponse.<SignupResponseDto>builder()
				.statusCode(HttpStatus.NO_CONTENT.value())
				.msg("회원가입에 실패했습니다.")
				.build());
		}

		SignupResponseDto responseDto = userService.signup(requestDto);
		return ResponseEntity.ok()
			.body(CommonResponse.<SignupResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("회원가입이 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 7단계 : 로그인
	// POST http://localhost:8080/api/users/login
	// {"userNickname":"ㅇㅇ", "userName":"user11111", "userPassword":"userPassword"}
	@PostMapping("/users/login")
	public ResponseEntity<CommonResponse<LoginResponseDto>>login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse res, BindingResult bindingResult){

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if(fieldErrors.size() > 0) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
			}
			return ResponseEntity.badRequest()
				.body(CommonResponse.<LoginResponseDto>builder()
					.statusCode(HttpStatus.NO_CONTENT.value())
					.msg("로그인에 실패했습니다.")
					.build());
		}

			LoginResponseDto responseDto = userService.login(requestDto, res);
			return ResponseEntity.ok()
			.body(CommonResponse.<LoginResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("로그인에 성공했습니다.")
				.data(responseDto)
				.build());
	}

}
