package com.sparta.schedulemanagementproject.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
	private String commentContents;
	private String userId;
	//private Long scheduleId;

	public CommentRequestDto(String commentContents, String userId) {
		this.commentContents = commentContents;
		this.userId = userId;
	}

}
