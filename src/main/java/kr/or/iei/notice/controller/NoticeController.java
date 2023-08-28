package kr.or.iei.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	
	//전체리스트 조회
	@GetMapping(value="/noticeList")
	public String noticeList() {
		return "/notice/noticeList";
	}
}
