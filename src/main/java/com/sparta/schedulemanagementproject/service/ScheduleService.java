package com.sparta.schedulemanagementproject.service;

import com.sparta.schedulemanagementproject.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementproject.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementproject.entity.Schedule;
import com.sparta.schedulemanagementproject.repository.ScheduleRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
		// RequestDto -> Entity
		Schedule schedule = new Schedule(requestDto);

		// DB 저장
		Schedule saveSchedule = scheduleRepository.save(schedule);

		// Entity -> ResponseDto
		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

		return scheduleResponseDto;
	}

	public ScheduleResponseDto getSchedule(Long id) {
		Schedule schedule = findSchedule(id);
		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
		return scheduleResponseDto;
	}

	public List<ScheduleResponseDto> getSchedules() {
		// DB 조회
		return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
	}

	@Transactional
	public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
		// 해당 메모가 DB에 존재하는지 확인
		Schedule schedule = findSchedule(id);

		// memo 내용 수정
		schedule.update(requestDto);

		return id;
	}

	public Long deleteSchedule(Long id) {
		// 해당 메모가 DB에 존재하는지 확인
		Schedule schedule = findSchedule(id);

		// memo 삭제
		scheduleRepository.delete(schedule);

		return id;
	}

	private Schedule findSchedule(Long id) {
		return scheduleRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.")
		);
	}

}
