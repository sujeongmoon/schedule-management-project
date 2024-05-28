package com.sparta.schedulemanagementproject.dto;

import java.time.LocalDateTime;

import com.sparta.schedulemanagementproject.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

	private Long commentId;
	private String commentContents;
	private String userId;
	private Long scheduleId;
	private LocalDateTime createdAt;

	public CommentResponseDto(Comment comment) {
		this.commentId = comment.getCommentId();
		this.commentContents = comment.getCommentContents();
		this.userId = comment.getUserId();
		this.scheduleId = comment.getSchedule().getScheduleId();
		this.createdAt = comment.getCreatedAt();
	}
}

