package com.sungmun.NoticeBoard.domain.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sungmun.NoticeBoard.domain.BaseTimeEntity;
import com.sungmun.NoticeBoard.domain.member.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseTimeEntity {
	@Id
	@GeneratedValue
	@Column(name = "notice_num", nullable = false)
	private Long num;
	@Column(name = "notice_title", length = 50, nullable = false)
	private String title;
	@Column(name = "notice_count", length = 16, nullable = false)
	private int count;
	@Column(name = "notice_contents", nullable = false)
	private String contents;
	@Column(name = "file_name")
	private String fileName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

}
