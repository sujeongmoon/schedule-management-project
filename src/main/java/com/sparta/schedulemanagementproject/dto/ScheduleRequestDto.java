package com.sparta.schedulemanagementproject.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
	private String title;
	private String contents;
	private String manager;
	private String password;
	private Date createdAt;
}
