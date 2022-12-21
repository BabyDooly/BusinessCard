package com.sungmun.NoticeBoard.dto.notice;

import com.sungmun.NoticeBoard.domain.member.Member;
import com.sungmun.NoticeBoard.domain.notice.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSaveRequestDto {
	private long num;
	private String title;
	private int count;
	private String member;
	private String contents;
	private String fileName;
	public Notice toEntity() {
		return Notice.builder()
				.num(num)
				.title(title)
				.count(count)
				.fileName(fileName)
				.member(Member.builder().id(member).build())
				.contents(contents)
				.build();
	}
}
