package com.sparta.schedulemanagementproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedulemanagementproject.CommonResponse;
import com.sparta.schedulemanagementproject.dto.CommentRequestDto;
import com.sparta.schedulemanagementproject.dto.CommentResponseDto;
import com.sparta.schedulemanagementproject.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// 2단계 : comment 등록
	// POST http://localhost:8080/api/schedules/{scheduleId}/comments
	// {"commentContents":"댓글 내용", "userId":"담당자 Id"}
	@PostMapping("/schedules/{scheduleId}/comments")
	public ResponseEntity<CommonResponse<CommentResponseDto>> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto) {
		CommentResponseDto responseDto = commentService.createComment(scheduleId, requestDto);
		return ResponseEntity.ok()
			.body(CommonResponse.<CommentResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("생성이 완료됐습니다.")
				.data(responseDto)
				.build());
	}


}
