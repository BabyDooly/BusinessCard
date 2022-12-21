package com.sungmun.NoticeBoard.domain;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sungmun.NoticeBoard.domain.member.Member;
import com.sungmun.NoticeBoard.domain.member.MemberRepository;
import com.sungmun.NoticeBoard.domain.member.MemberRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@After
	public void cleanup() {
		memberRepository.deleteAll();
	}

	@Test
	public void insertMember() {
		for (int i = 0; i < 100; i++) {

			MemberRole role = new MemberRole();
			if (i <= 80) {
				role.setRoleName("BASIC");
			} else if (i <= 90) {
				role.setRoleName("MANAGER");
			} else {
				role.setRoleName("ADMIN");
			}

			Member member = Member.builder()
					.id("id" + i)
					.password("password" + i)
					.firstName("firstName" + i)
					.secondName("secondName" + i)
					.phone("phone" + i)
					.email("email@naver.com" + i)
					.roles(Arrays.asList(role))
					.build();
			memberRepository.save(member);
		}
	}

	@Test
	public void testMember() {
		Optional<Member> result=memberRepository.findById("id50");
		result.ifPresent(member->System.err.println("member "+member));
	}
}
