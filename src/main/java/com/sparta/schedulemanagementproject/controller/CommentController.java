package com.sparta.schedulemanagementproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedulemanagementproject.CommonResponse;
import com.sparta.schedulemanagementproject.dto.CommentRequestDto;
import com.sparta.schedulemanagementproject.dto.CommentResponseDto;
import com.sparta.schedulemanagementproject.jwt.JwtUtil;
import com.sparta.schedulemanagementproject.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// 2단계 : comment 등록
	// POST http://localhost:8080/api/schedules/{scheduleId}/comments
	// {"commentContents":"댓글 내용", "userId":1}
	// 로그인 시도 후 받은 토큰값을 헤더에 넣기 - [ Authorization / Bearer eyJhbGciOiJIUzI1NiJ9~ ]
	@PostMapping("/schedules/{scheduleId}/comments")
	public ResponseEntity<CommonResponse<CommentResponseDto>> createComment(@PathVariable Long scheduleId, @RequestBody @Valid CommentRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
		CommentResponseDto responseDto = commentService.createComment(scheduleId, requestDto, tokenValue);
		return ResponseEntity.ok()
			.body(CommonResponse.<CommentResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("생성이 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 3단계 : comment 수정
	// PUT http://localhost:8080/api/schedules/comments/{commentId}
	// {"commentContents":"댓글 내용 수정", "userId":1}
	// 로그인 시도 후 받은 토큰값을 헤더에 넣기 - [ Authorization / Bearer eyJhbGciOiJIUzI1NiJ9~ ]
	@PutMapping("/schedules/comments/{commentId}")
	public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
		CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto, tokenValue);
		return ResponseEntity.ok()
			.body(CommonResponse.<CommentResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("수정이 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 4단계 : comment 삭제
	// PUT http://localhost:8080/api/schedules/comments/{commentId}
	// {"userId":1}
	// 로그인 시도 후 받은 토큰값을 헤더에 넣기 - [ Authorization / Bearer eyJhbGciOiJIUzI1NiJ9~ ]
	@DeleteMapping("/schedules/comments/{commentId}")
	public ResponseEntity<CommonResponse> deleteMemo(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
		commentService.deleteComment(commentId, requestDto, tokenValue);
		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.statusCode(HttpStatus.OK.value())
				.msg(commentId + "번 댓글의 삭제가 완료됐습니다.")
				.build());
	}
}
