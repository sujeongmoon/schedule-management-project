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
		Schedule schedule = new Schedule(requestDto);
		Schedule saveSchedule = scheduleRepository.save(schedule);
		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

		return scheduleResponseDto;
	}

	public ScheduleResponseDto getSchedule(Long scheduleId) {
		Schedule schedule = findSchedule(scheduleId);
		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
		return scheduleResponseDto;
	}

	public List<ScheduleResponseDto> getSchedules() {
		return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
	}

	@Transactional
	public Long updateSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
		Schedule schedule = findSchedule(scheduleId);

		if (schedule.checkPassword(requestDto)){
			schedule.update(requestDto);
		} else throw new IllegalArgumentException("입력하신 비밀번호가 틀렸습니다.");

		return scheduleId;
	}

	public Long deleteSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
		Schedule schedule = findSchedule(scheduleId);

		if (schedule.checkPassword(requestDto)){
			scheduleRepository.delete(schedule);
		} else throw new IllegalArgumentException("입력하신 비밀번호가 틀렸습니다.");

		return scheduleId;
	}

	public Schedule findSchedule(Long scheduleId) {
		return scheduleRepository.findById(scheduleId).orElseThrow(() ->
			new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.")
		);
	}
}
