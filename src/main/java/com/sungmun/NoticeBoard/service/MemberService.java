package com.sungmun.NoticeBoard.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sungmun.NoticeBoard.dto.member.MemberMainResponseDto;
import com.sungmun.NoticeBoard.dto.notice.NoticeMainResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sungmun.NoticeBoard.domain.member.MemberRepository;
import com.sungmun.NoticeBoard.dto.member.MemberSaveRequestDto;
import com.sungmun.NoticeBoard.dto.member.MemberUpdateDto;
import com.sungmun.NoticeBoard.dto.member.SecurityMember;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MemberService implements UserDetailsService {
	private MemberRepository repository;
	
	public String update(MemberUpdateDto dto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return repository.save(dto.toEntity()).getId();
	}

	public String save(MemberSaveRequestDto dto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return repository.save(dto.toEntity()).getId();
	}

	public MemberUpdateDto findById(String id) throws NoSuchElementException {
		return repository.findById(id).map(MemberUpdateDto::new).get();
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return Optional.ofNullable(repository.findById(id)).get()
				.filter(m -> m!=null)
				.map(m -> new SecurityMember(m))
				.get();
				
	}

	@Transactional(readOnly=true)
	public List<MemberMainResponseDto> findAll(Pageable pageable){
		return repository.findAll(pageable)
				.stream()
				.map(MemberMainResponseDto::new)
				.collect(Collectors.toList());
	}
}
