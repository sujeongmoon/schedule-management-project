package com.sparta.schedulemanagementproject.entity;

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
	@Column(nullable = false)
	private String commentTitle;
	@Column(nullable = false, length = 500)
	private String commentContents;
	@Column(nullable = false)
	private String userId;

	@ManyToOne
	@JoinColumn(name="scheduleId")
	private Schedule schedule;

/*	public Comment(CommentRequestDto requestDto) {
		this.commentContents = requestDto.getCommentContents();
		this.userId = requestDto.getUserId();
	}*/
/*

	public void update(CommentRequestDto requestDto) {
		this.scheduleTitle = requestDto.getScheduleTitle();
		this.scheduleContents = requestDto.getScheduleContents();
		this.userName = requestDto.getUserName();
		this.schedulePassword = requestDto.getSchedulePassword();
	}

	// schedule의 비밀번호와 입력받은 객체의 비밀번호가 같은지 비교
	public boolean checkPassword(ScheduleRequestDto requestDto) {
		return this.getSchedulePassword().equals(requestDto.getSchedulePassword());
	}
*/

}

