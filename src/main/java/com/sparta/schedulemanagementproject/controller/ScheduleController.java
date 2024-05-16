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

	@GetMapping("/schedules")
	public List<ScheduleResponseDto> getSchedules() {
		return scheduleService.getSchedules();
	}

	@PutMapping("/schedule/{id}")
	public long updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		return scheduleService.updateSchedule(id, requestDto); ////???!?
	}

	@DeleteMapping("/schedule/{id}")
	public Long deleteMemo(@PathVariable Long id) {
		return scheduleService.deleteSchedule((id));
	}
}

