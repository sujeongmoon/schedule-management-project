package com.sparta.schedulemanagementproject.service;

import org.springframework.stereotype.Service;

import com.sparta.schedulemanagementproject.dto.CommentRequestDto;
import com.sparta.schedulemanagementproject.dto.CommentResponseDto;
import com.sparta.schedulemanagementproject.entity.Comment;
import com.sparta.schedulemanagementproject.entity.Schedule;
import com.sparta.schedulemanagementproject.repository.CommentRepository;
import com.sparta.schedulemanagementproject.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CommentService {


	private final CommentRepository commentRepository;
	private final ScheduleRepository scheduleRepository;

	public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {

		Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
			() -> new NullPointerException("해당하는 스케줄이 없습니다.")
		);
		Comment comment = new Comment(requestDto, schedule);

		if (!comment.checkContentsNull(requestDto)){
			Comment saveComment = commentRepository.save(comment);
			CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
			return commentResponseDto;
		} else throw new IllegalArgumentException("댓글의 comment가 공백이 되어서는 안됩니다.");
	}
}
