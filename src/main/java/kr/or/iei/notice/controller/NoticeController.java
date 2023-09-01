package kr.or.iei.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeListData;

@Controller
@RequestMapping(value="/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	//전체리스트 조회
	@GetMapping(value="/noticeList")
	public String noticeList(Model model, int reqPage) {
		NoticeListData nld = noticeService.searchAllNotice(reqPage);
		model.addAttribute("list",nld.getNoticeList());
		model.addAttribute("pageNavi",nld.getPagaNavi());
		return "/notice/noticeList";
	}
	@GetMapping(value="/noticeView")
	public String noticeView(int noticeNo, Model model) {		
		Notice n = noticeService.selectOneNotice(noticeNo);		
		if(n != null) {
			model.addAttribute("n",n);		
			return "/notice/noticeView";
		}else {
			model.addAttribute("title", "조회실패");
			model.addAttribute("msg", "이미삭제된 게시물입니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/notice/noticeList?reqPage=1");
			return "common/msg";
		}
		//List viewList = qnaService.selectOneQna(questionNo);
	}
}
