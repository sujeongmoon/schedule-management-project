package com.sparta.schedulemanagementproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.schedulemanagementproject.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
