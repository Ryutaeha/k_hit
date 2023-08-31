package kr.or.iei.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.qna.model.service.QnaService;
import kr.or.iei.qna.model.vo.Qna;
import kr.or.iei.qna.model.vo.QnaListData;
import kr.or.iei.qna.model.vo.QnaViewData;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value="/qna")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	//QnA전제출력페이지
	@GetMapping(value="/qnaList")
	public String qnaView(Model model, int reqPage) {
		QnaListData qld = qnaService.searchAllQna(reqPage);
		model.addAttribute("list",qld.getQnaList());
		model.addAttribute("pageNavi", qld.getPageNavi());
		return "/qna/qnaList";
	}
	//qna상세보기
	@GetMapping(value="/qnaView")
	public String qnaView(int questionNo, Model model,@SessionAttribute(required = false)Seller s,@SessionAttribute(required = false) Customer c) {
		int sellerNo = (s == null)? 0 : s.getSellerNo();
		int customerNo = (c == null)? 0 : c.getCustomerNo();
		QnaViewData qvd = qnaService.selectOneQna(questionNo,sellerNo,customerNo);
		//List viewList = qnaService.selectOneQna(questionNo);
		return null;
	}
	//qna글작성페이지 이동
	@GetMapping(value="/qnaFrmEditor")
	public String qnaFrmEditor() {
		return "/qna/qnaFrmEditor";
	}
	//qna글작성 insert
	@PostMapping(value = "/qnaWrite")
	public String insertQna(Qna q,Model model,@SessionAttribute(required = false)Seller s,@SessionAttribute(required = false) Customer c) {
				
		int result = qnaService.insertQna(q,s,c);
		if(result>0) {
			model.addAttribute("title", "문의사항 작성 성공");
			model.addAttribute("msg", "문의사항이 작성되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "문의사항 작성 실패");
			model.addAttribute("msg", "문의사항이 작성이 실패되었습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/qna/qnaList?reqPage=1");
		return "common/msg";
	}
}
