package com.sungmun.NoticeBoard.dto.notice;

import com.sungmun.NoticeBoard.domain.notice.Notice;
import com.sungmun.NoticeBoard.dto.BaseTimeDto;

import lombok.Getter;

@Getter
public class NoticeReadResponseDto{
	private Long num;
	private String title;
	private String contents;
	private String fileName;
	private String user;
	private String createDate;
	
	public NoticeReadResponseDto(Notice entity) {
		this.createDate=BaseTimeDto.toStringLocalDateTime(entity.getCreateDate(), "yyyy-MM-dd");
		this.num=entity.getNum();
		this.title=entity.getTitle();
		this.contents=entity.getContents();
		this.fileName=entity.getFileName();
		this.user=entity.getMember().getId();
	}
	
}
