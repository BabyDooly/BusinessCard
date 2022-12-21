package com.sungmun.NoticeBoard.dto.member;

import com.sungmun.NoticeBoard.domain.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {
	private String id;
	private String password;
	private String Name;
	private String phone;
	private String email;

	public MemberUpdateDto(Member entity) {
		this.id = entity.getId();
		this.password = entity.getPassword();
		this.Name = entity.getName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
	}
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.password(password)
				.Name(Name)
				.phone(phone)
				.email(email)
				.build();
	}
}
