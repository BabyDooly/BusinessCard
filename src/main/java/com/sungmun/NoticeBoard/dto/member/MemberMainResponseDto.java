package com.sungmun.NoticeBoard.dto.member;

import com.sungmun.NoticeBoard.domain.member.Member;
import com.sungmun.NoticeBoard.domain.notice.Notice;
import com.sungmun.NoticeBoard.dto.BaseTimeDto;
import lombok.Getter;

@Getter
public class MemberMainResponseDto {
	private int num;
	private String id;
	private String name;
	private String phone;
	private String email;
	private String createDate;

	public MemberMainResponseDto(Member entity) {
		this.createDate = BaseTimeDto.toStringLocalDateTime(entity.getCreateDate(), "yyyy-MM-dd");
		this.num = entity.getNum();
		this.id = entity.getId();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
	}
}
