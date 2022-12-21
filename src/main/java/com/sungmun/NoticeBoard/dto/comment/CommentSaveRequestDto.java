package com.sungmun.NoticeBoard.dto.comment;

import com.sungmun.NoticeBoard.domain.comment.Comment;
import com.sungmun.NoticeBoard.domain.member.Member;
import com.sungmun.NoticeBoard.domain.notice.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveRequestDto {
	private String contents;
	private Long notice;
	private String member;
	
	public Comment toEntity() {
		return Comment.builder()
				.contents(contents)
				.notice(Notice.builder().num(notice).build())
				.member(Member.builder().id(member).build())
				.build();
	}
}
