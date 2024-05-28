package com.sparta.schedulemanagementproject.entity;

import com.sparta.schedulemanagementproject.dto.CommentRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	@Column(nullable = false, length = 500)
	private String commentContents;
	@Column(nullable = false)
	private String userId;

	@ManyToOne
	@JoinColumn(name="scheduleId")
	private Schedule schedule;


	public Comment(CommentRequestDto requestDto, Schedule schedule) {
		this.commentContents = requestDto.getCommentContents();
		this.userId = requestDto.getUserId();
		this.schedule = schedule;
	}

	public void update(CommentRequestDto requestDto) {
		this.commentContents = requestDto.getCommentContents();
	}

	// 댓글 내용이 비어있는지 확인
	public boolean checkContentsNull(CommentRequestDto requestDto) {
		return "".equals(requestDto.getCommentContents());
	}

	// 담당자 id가 일치하는지 확인
	public boolean checkUserIdEquals(CommentRequestDto requestDto){
		return this.getUserId().equals(requestDto.getUserId());
	}

}

