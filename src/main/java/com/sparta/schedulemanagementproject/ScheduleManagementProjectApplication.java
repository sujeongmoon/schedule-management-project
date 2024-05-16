package com.sparta.schedulemanagementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleManagementProjectApplication.class, args);
	}

}
