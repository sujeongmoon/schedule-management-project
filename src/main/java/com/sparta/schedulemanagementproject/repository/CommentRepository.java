package com.sparta.schedulemanagementproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.schedulemanagementproject.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
