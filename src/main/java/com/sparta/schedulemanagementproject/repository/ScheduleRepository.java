package com.sparta.schedulemanagementproject.repository;

import java.util.List;

import com.sparta.schedulemanagementproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findAllByOrderByCreatedAtDesc();
}
