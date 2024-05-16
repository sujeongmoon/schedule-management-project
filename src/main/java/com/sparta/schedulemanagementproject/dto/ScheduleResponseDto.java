package com.sparta.schedulemanagementproject.dto;

import java.util.Date;

import com.sparta.schedulemanagementproject.entity.Schedule;

import lombok.Getter;
@Getter
public class ScheduleResponseDto {
	private Long id;
	private String title;
	private String contents;
	private String manager;
	private Date createdAt;

	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.contents = schedule.getContents();
		this.manager = schedule.getManager();
		this.createdAt = schedule.getCreatedAt();
	}
}
