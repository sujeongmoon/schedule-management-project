package com.sparta.schedulemanagementproject.repository;

import com.sparta.schedulemanagementproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


// @Repository 달려있음
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
