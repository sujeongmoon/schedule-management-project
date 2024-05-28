package com.sparta.schedulemanagementproject.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.sparta.schedulemanagementproject.entity.Schedule;

import lombok.Getter;
@Getter
public class ScheduleResponseDto {
	private Long scheduleId;
	private String scheduleTitle;
	private String scheduleContents;
	private String userName;
	private LocalDateTime createdAt;

	public ScheduleResponseDto(Schedule schedule) {
		this.scheduleId = schedule.getScheduleId();
		this.scheduleTitle = schedule.getScheduleTitle();
		this.scheduleContents = schedule.getScheduleContents();
		this.userName = schedule.getUserName();
		this.createdAt = schedule.getCreatedAt();
	}
}
