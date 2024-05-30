package com.sparta.schedulemanagementproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
	@NotBlank(message ="댓글 내용은 비어있어서는 안 됩니다.")
	private String commentContents;
	private Long userId;

	public CommentRequestDto(String commentContents, Long userId) {
		this.commentContents = commentContents;
		this.userId = userId;
	}
}
