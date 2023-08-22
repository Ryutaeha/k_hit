package kr.or.iei.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.qna.model.service.QnaService;

@Controller
@RequestMapping(value="/qna")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@GetMapping(value="/qnaView")
	public String qnaView() {
		return "/qna/qnaView";
	}
}
