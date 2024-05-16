package com.sparta.schedulemanagementproject.entity;

import java.util.Date;

import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "schedule") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Schedule{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //PK 자동생성
	private Long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "contents", nullable = false, length = 500)
	private String contents;
	@Column(name = "manager", nullable = false)
	private String manager;
	@Column(name = "password", nullable = false, updatable = false)
	private String password;
	@Temporal(TemporalType.DATE)//SQL에서 date타입으로 받기
	@Column(name = "createdAt", nullable = false, updatable = false)
	private Date createdAt;


	public Schedule(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.manager = requestDto.getManager();
		this.password = requestDto.getPassword();
		this.createdAt = requestDto.getCreatedAt();
	}

	public void update(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.manager = requestDto.getManager();
		this.password = requestDto.getPassword();
	}

}

