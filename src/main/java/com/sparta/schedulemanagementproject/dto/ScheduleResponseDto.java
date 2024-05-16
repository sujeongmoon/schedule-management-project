package com.sparta.schedulemanagementproject.dto;

import java.time.LocalDateTime;

import com.sparta.schedulemanagementproject.entity.Schedule;

import lombok.Getter;
@Getter
public class ScheduleResponseDto {
	private Long id;
	private String title;
	private String contents;
	private String manager;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.contents = schedule.getContents();
		this.manager = schedule.getManager();
		this.password = schedule.getPassword();
		this.createdAt = schedule.getCreatedAt();
		this.modifiedAt = schedule.getModifiedAt();
	}
}
