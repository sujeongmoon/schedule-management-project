package com.sparta.schedulemanagementproject.controller;

import com.sparta.schedulemanagementproject.CommonResponse;
import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementproject.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementproject.service.ScheduleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
	private final ScheduleService scheduleService;

	// POST http://localhost:8080/api/schedules
	// {"scheduleTitle":"제목", "scheduleContents":"내용", "userName":"담당자", "schedulePassword":"1234"}
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

	// GET http://localhost:8080/api/schedules/1
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

	// PUT http://localhost:8080/api/schedules/1
	// {"scheduleTitle":"제목", "scheduleContents":"내용수정", "userName":"담당자", "schedulePassword":"1234"}
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

	// DELETE http://localhost:8080/api/schedules/1
	// {"schedulePassword":"1234"}
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

