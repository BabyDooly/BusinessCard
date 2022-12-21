package com.sungmun.NoticeBoard.web;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.sungmun.NoticeBoard.dto.notice.NoticeMainResponseDto;
import com.sungmun.NoticeBoard.service.NoticeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	private NoticeService noticeService;

	@GetMapping("/")
	public String main(Model model)
			throws IOException {
		TemplateLoader loader = new ClassPathTemplateLoader();
		loader.setPrefix("/templates");
		loader.setSuffix(".hbs");

		Handlebars handlebarsEngine = new Handlebars(loader);

		Template listLayer = handlebarsEngine.compile("/notice/list");

		model.addAttribute("listTemplate", listLayer.text());
		model.addAttribute("notice", noticeService.findAll(PageRequest.of(0, 20, Direction.DESC, "num")));
		model.addAttribute("pagecount", noticeService.pageCount(PageRequest.of(0, 20, Direction.DESC, "num")));
		model.addAttribute("nowpage", 0);
		return "index";
	}

	@GetMapping("/list")
	@ResponseBody
	public List<NoticeMainResponseDto> mainAjax(@PageableDefault(sort = {
			"num" }, direction = Direction.DESC, size = 20, page = 0) Pageable pageable) {
		return noticeService.findAll(pageable);
	}
}
