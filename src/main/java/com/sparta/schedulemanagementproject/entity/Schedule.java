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
		this.password = requestDto.getPassword();
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.manager = requestDto.getManager();
		this.createdAt = requestDto.getCreatedAt();
	}

	public void update(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.manager = requestDto.getManager();
		this.password = requestDto.getPassword();
	}

	// schedule의 비밀번호와 입력받은 객체의 비밀번호가 같은지 비교
	public boolean checkPassword(ScheduleRequestDto requestDto) {
		return this.getPassword().equals(requestDto.getPassword());
	}

}

