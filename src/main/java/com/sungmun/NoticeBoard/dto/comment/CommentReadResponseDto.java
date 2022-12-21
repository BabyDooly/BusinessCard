package com.sungmun.NoticeBoard.dto.comment;

import com.sungmun.NoticeBoard.domain.comment.Comment;
import com.sungmun.NoticeBoard.dto.BaseTimeDto;

import lombok.Getter;

@Getter
public class CommentReadResponseDto {
	private Long num;
	private String contents;
	private String user;
	private String createDate;
	
	public CommentReadResponseDto(Comment entity) {
		this.createDate=BaseTimeDto.toStringLocalDateTime(entity.getCreateDate(), "yyyy-MM-dd");
		this.num=entity.getNum();
		this.contents=entity.getContents();
		this.user=entity.getMember().getId();
	}
}
