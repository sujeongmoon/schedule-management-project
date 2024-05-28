package com.sparta.schedulemanagementproject.entity;

import java.util.ArrayList;
import java.util.List;

import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;
	@Column(nullable = false)
	private String scheduleTitle;
	@Column(nullable = false, length = 500)
	private String scheduleContents;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false, updatable = false)
	private String schedulePassword;

	@OneToMany(mappedBy = "schedule")
	private List<Comment> commentList = new ArrayList<>();

	public void addCommentList(Comment comment) {
		this.commentList.add(comment);
		comment.setSchedule(this);
	}

	public Schedule(ScheduleRequestDto requestDto) {
		this.schedulePassword = requestDto.getSchedulePassword();
		this.scheduleTitle = requestDto.getScheduleTitle();
		this.scheduleContents = requestDto.getScheduleContents();
		this.userName = requestDto.getUserName();
	}

	public void update(ScheduleRequestDto requestDto) {
		this.scheduleTitle = requestDto.getScheduleTitle();
		this.scheduleContents = requestDto.getScheduleContents();
		this.userName = requestDto.getUserName();
		this.schedulePassword = requestDto.getSchedulePassword();
	}

	public boolean checkPassword(ScheduleRequestDto requestDto) {
		return this.getSchedulePassword().equals(requestDto.getSchedulePassword());
	}

}

