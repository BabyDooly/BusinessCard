package com.sungmun.NoticeBoard.web;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungmun.NoticeBoard.dto.comment.CommentReadResponseDto;
import com.sungmun.NoticeBoard.dto.comment.CommentSaveRequestDto;
import com.sungmun.NoticeBoard.service.CommentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/comment")
@ResponseBody
public class CommentController {
	CommentService commentService;

	@GetMapping("/read")
	public List<CommentReadResponseDto> read(@RequestParam long num) {
		return commentService.findByid(num);
	}

	@PostMapping("/write")
	public List<CommentReadResponseDto> write(Principal principal, @RequestBody CommentSaveRequestDto dto) {
		dto.setMember(principal.getName());
		commentService.save(dto);

		return commentService.findByid(dto.getNotice());
	}

}
