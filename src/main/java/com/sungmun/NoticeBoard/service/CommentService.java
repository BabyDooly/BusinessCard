package com.sungmun.NoticeBoard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sungmun.NoticeBoard.domain.comment.CommentRepository;
import com.sungmun.NoticeBoard.dto.comment.CommentReadResponseDto;
import com.sungmun.NoticeBoard.dto.comment.CommentSaveRequestDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentService {
	private CommentRepository repository;

	@Transactional(readOnly = true)
	public List<CommentReadResponseDto> findByid(final long noticeId) {
		return repository.findAll()
				.stream()
				.filter(comment -> (comment.getNotice().getNum() == noticeId))
				.map(CommentReadResponseDto::new).collect(Collectors.toList());
	}

	public Long save(final CommentSaveRequestDto dto) {
		return repository.save(dto.toEntity()).getNum();
	}
}
