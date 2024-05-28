package com.sparta.schedulemanagementproject.controller;

import com.sparta.schedulemanagementproject.CommonResponse;
import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementproject.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementproject.service.ScheduleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	// POST http://localhost:8080/api/schedules
	// {"title":"제목", "contents":"내용", "manager":"담당자", "password":"1234"}
	@PostMapping("/schedules")
	public ResponseEntity<CommonResponse<ScheduleResponseDto>> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
		ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
		return ResponseEntity.ok()
			.body(CommonResponse.<ScheduleResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("생성이 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 2단계 : 선택한 일정 조회
	// GET http://localhost:8080/api/schedules/{id}
	@GetMapping("/schedules/{id}")
	public ResponseEntity<CommonResponse<ScheduleResponseDto>> getSchedule(@PathVariable Long id) {
		ScheduleResponseDto responseDto = scheduleService.getSchedule(id);
		return ResponseEntity.ok()
			.body(CommonResponse.<ScheduleResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("조회가 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 3단계 : 일정 목록 조회 (내림차순)
	// GET http://localhost:8080/api/schedules
	@GetMapping("/schedules")
	public ResponseEntity<CommonResponse<List<ScheduleResponseDto>>> getSchedules() {
		List<ScheduleResponseDto> responseDtoList = scheduleService.getSchedules();
		return ResponseEntity.ok()
			.body(CommonResponse.<List<ScheduleResponseDto>>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("목록 조회가 완료됐습니다.")
				.data(responseDtoList)
				.build());
	}

	// 4단계 : 선택한 일정 수정
	// PUT http://localhost:8080/api/schedules/{id}
	// {"title":"제목2", "contents":"내용2", "manager":"수정", "password":"1234"}
	@PutMapping("/schedules/{id}")
	public ResponseEntity<CommonResponse<ScheduleResponseDto>> updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		scheduleService.updateSchedule(id, requestDto);
		ScheduleResponseDto responseDto = scheduleService.getSchedule(id);
		return ResponseEntity.ok()
			.body(CommonResponse.<ScheduleResponseDto>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("수정이 완료됐습니다.")
				.data(responseDto)
				.build());
	}

	// 5단계 : 선택한 일정 삭제
	// DELETE http://localhost:8080/api/schedule/{id}
	// {"password":"1234"}
	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<CommonResponse> deleteMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		scheduleService.deleteSchedule(id, requestDto);
		return ResponseEntity.ok()
			.body(CommonResponse.builder()
				.statusCode(HttpStatus.OK.value())
				.msg(id + "번의 삭제가 완료됐습니다.")
				.build());
	}
}

