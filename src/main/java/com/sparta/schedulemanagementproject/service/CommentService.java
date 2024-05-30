package com.sparta.schedulemanagementproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.schedulemanagementproject.dto.CommentRequestDto;
import com.sparta.schedulemanagementproject.dto.CommentResponseDto;
import com.sparta.schedulemanagementproject.entity.Comment;
import com.sparta.schedulemanagementproject.entity.Schedule;
import com.sparta.schedulemanagementproject.entity.User;
import com.sparta.schedulemanagementproject.jwt.JwtUtil;
import com.sparta.schedulemanagementproject.repository.CommentRepository;
import com.sparta.schedulemanagementproject.repository.ScheduleRepository;
import com.sparta.schedulemanagementproject.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, String tokenValue) {

		Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
			() -> new NullPointerException("해당하는 스케줄이 없습니다.")
		);

		checkToken(tokenValue, requestDto);

		Comment comment = new Comment(requestDto, schedule);

		if (!comment.checkContentsNull(requestDto)) {
			Comment saveComment = commentRepository.save(comment);
			CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
			return commentResponseDto;
		} else
			throw new IllegalArgumentException("댓글의 comment가 공백이 되어서는 안됩니다.");
	}

	@Transactional
	public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, String tokenValue) {

		checkToken(tokenValue, requestDto);

		Comment comment = findComment(commentId);

		if (comment.checkUserIdEquals(requestDto)) {
			comment.update(requestDto);
		} else
			throw new IllegalArgumentException("입력하신 유저의 id가 댓글의 유저id와 다릅니다.");

		CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

		return commentResponseDto;
	}

	public Long deleteComment(Long commentId, CommentRequestDto requestDto, String tokenValue) {

		checkToken(tokenValue, requestDto);

		Comment comment = findComment(commentId);

		if (comment.checkUserIdEquals(requestDto)) {
			commentRepository.delete(comment);
		} else
			throw new IllegalArgumentException("입력하신 담당자 ID가 저장된 담당자 ID와 다릅니다.");

		return commentId;
	}

	public Comment findComment(Long commentId) {
		return commentRepository.findById(commentId).orElseThrow(() ->
			new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
		);
	}

	public void checkToken(String tokenValue, CommentRequestDto requestDto) {
		String token = jwtUtil.substringToken(tokenValue);
		if (!jwtUtil.validateToken(token)) {
			throw new IllegalArgumentException("Token Error");
		}
		Claims info = jwtUtil.getUserInfoFromToken(token);

		String userName = info.getSubject();

		User user = userRepository.findByUserName(userName).orElseThrow(
			() -> new NullPointerException("해당하는 유저가 없습니다.")
		);

		if (user.getUserId() != requestDto.getUserId()) {
			System.out.println("user.getUserId() = " + user.getUserId());
			System.out.println("requestDto.getUserId() = " + requestDto.getUserId());
			throw new IllegalArgumentException("로그인된 유저의 id와 입력받은 유저의 id가 다릅니다.");
		}
	}
}
