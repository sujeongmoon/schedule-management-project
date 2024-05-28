package com.sparta.schedulemanagementproject.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
	private String scheduleTitle;
	private String scheduleContents;
	private String userName;
	private String schedulePassword;
}
