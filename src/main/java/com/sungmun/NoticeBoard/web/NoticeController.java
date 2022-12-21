package com.sungmun.NoticeBoard.web;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.sungmun.NoticeBoard.dto.notice.NoticeReadResponseDto;
import com.sungmun.NoticeBoard.dto.notice.NoticeSaveRequestDto;
import com.sungmun.NoticeBoard.service.NoticeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	NoticeService noticeService;

	@GetMapping("/read")
	public String read(Model model, Principal principal, @RequestParam long num) throws IOException {
		TemplateLoader loader = new ClassPathTemplateLoader();
		loader.setPrefix("/templates");
		loader.setSuffix(".hbs");

		Handlebars handlebarsEngine = new Handlebars(loader);

		Template commentLayer = handlebarsEngine.compile("/notice/comment/read");
		model.addAttribute("commentTemplate", commentLayer.text());

		NoticeReadResponseDto read = noticeService.findById(num);
		model.addAttribute("notice", read);

		if (principal != null && principal.getName().equals(read.getUser())) {
			model.addAttribute("author", true);
		}

		return "/notice/readLayout";
	}

	@GetMapping("/update")
	public String update(Model model, @RequestParam long num) {
		model.addAttribute("notice", noticeService.findById(num));
		return "/notice/update";
	}

	@GetMapping("/write")
	public String write() {
		return "notice/write";
	}

	@PostMapping("/write")
	public String save(NoticeSaveRequestDto dto, Principal principal, RedirectAttributes model) {
		dto.setMember(principal.getName());
		model.addAttribute("num", noticeService.save(dto));
		return "redirect:/notice/read";
	}

}
