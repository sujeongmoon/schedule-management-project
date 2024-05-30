package com.sparta.schedulemanagementproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.schedulemanagementproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String username);
}
