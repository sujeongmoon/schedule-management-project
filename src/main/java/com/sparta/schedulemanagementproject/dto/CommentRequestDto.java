package com.sparta.schedulemanagementproject.dto;
import lombok.Getter;

@Getter
public class CommentRequestDto {
	private String scheduleTitle;
	private String scheduleContents;
	private String userName;
	private String schedulePassword;
}
