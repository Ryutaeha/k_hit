package kr.or.iei.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.qna.model.service.QnaService;

@Controller
@RequestMapping(value="/qna")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	//QnA전제출력페이지
	@GetMapping(value="/qnaList")
	public String qnaView(Model model) {
		List qnaList = qnaService.searchAllQna();
		model.addAttribute("list",qnaList);
		return "/qna/qnaList";
	}
	
	@GetMapping(value="/qnaDetail")
	public String qnaDetail() {
		return "/qna/qnaDetail";
	}
	
	@GetMapping(value="/qnaFrmEditor")
	public String qnaFrmEditor() {
		return "/qna/qnaFrmEditor";
	}
}
