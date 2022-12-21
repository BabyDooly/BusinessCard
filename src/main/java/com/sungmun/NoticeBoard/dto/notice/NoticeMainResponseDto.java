package com.sungmun.NoticeBoard.dto.notice;

import com.sungmun.NoticeBoard.domain.notice.Notice;
import com.sungmun.NoticeBoard.dto.BaseTimeDto;

import lombok.Getter;

@Getter
public class NoticeMainResponseDto{
	private Long num;
	private String title;
	private String user;
	private String createDate;
	
	public NoticeMainResponseDto(Notice entity) {
		this.createDate=BaseTimeDto.toStringLocalDateTime(entity.getCreateDate(), "yyyy-MM-dd");
		num=entity.getNum();
		title=entity.getTitle();
		user=entity.getMember().getId();
	}
}
