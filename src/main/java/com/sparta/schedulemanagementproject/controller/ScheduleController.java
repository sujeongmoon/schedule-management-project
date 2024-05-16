package com.sparta.schedulemanagementproject.controller;

import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementproject.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementproject.service.ScheduleService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //
@RequestMapping("/api")
public class ScheduleController {
	private final ScheduleService scheduleService;

	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	// 1단계 : 일정 작성
	// POST http://localhost:8080/api/schedule
	// {"title":"제목", "contents":"내용", "manager":"담당자", "password":"1234", "createdAt" : "2024-05-17"}
	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
		return scheduleService.createSchedule(requestDto);
	}

	// 2단계 : 선택한 일정 조회
	// GET http://localhost:8080/api/schedule/{id}
	@GetMapping("/schedule/{id}")
	public ScheduleResponseDto getSchedule(@PathVariable Long id) {
		return scheduleService.getSchedule(id);
	}

	// 3단계 : 일정 목록 조회 (내림차순)
	// GET http://localhost:8080/api/schedules
	@GetMapping("/schedules")
	public List<ScheduleResponseDto> getSchedules() {
		return scheduleService.getSchedules();
	}

	// 4단계 : 선택한 일정 수정
	// PUT http://localhost:8080/api/schedule/{id}
	// {"title":"제목2", "contents":"내용2", "manager":"수정", "password":"1234"}
	@PutMapping("/schedule/{id}")
	public ScheduleResponseDto updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		scheduleService.updateSchedule(id, requestDto);
		return scheduleService.getSchedule(id);
	}

	// 5단계 : 선택한 일정 삭제
	// DELETE http://localhost:8080/api/schedule/{id}
	// {"password":"1234"}
	@DeleteMapping("/schedule/{id}")
	public Long deleteMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		return scheduleService.deleteSchedule(id, requestDto);
	}
}

