package kr.or.iei.page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.page.model.service.PageService;
import kr.or.iei.page.model.vo.PageSearch;
import kr.or.iei.page.model.vo.PageSearchDate;

@Controller
@RequestMapping(value="/page")
public class PageController {
	@Autowired
	private PageService pageService;
	
	@GetMapping(value="/search")
	public String productSearch(String searchWord,Model model) {
		model.addAttribute("searchWord",searchWord);		
		if(searchWord.equals("")) {
			 searchWord = "뷐";
		}	
		//하고자 하는 행위 : 검색창에 input 하면 productName에 1글자라도 겹치면 count세서 
		//총 갯수,검색된 모든 product 목록화
		PageSearchDate psd = pageService.searchProduct(searchWord);
		model.addAttribute("searchCount", psd.getSearchCount());
		model.addAttribute("searchList", psd.getSearchList());
		//리뷰카운트 조회
		System.out.println("검색한 단어 조회 : "+psd);
		return "/page/search";
		
	}
	//베스트 페이지
	@GetMapping(value="/best")
	public String bestPage() {
		return "/page/best";
	}
	//신상품 페이지
	@GetMapping(value="/new")
	public String newPage() {
		return "/page/new";
	}
	//모든 상품페이지
	@GetMapping(value="/all")
	public String allPage() {
		return "/page/all";
	}
	//귀걸이 페이지
	@GetMapping(value="/earring")
	public String earringPage() {
		return "/page/earring";
	}
	//목걸이 페이지
	@GetMapping(value="/necklace")
	public String necklacePage() {
		return "/page/necklace";
	}
	//반지 페이지
	@GetMapping(value="/ring")
	public String ringPage() {
		return "/page/ring";
	}
	//팔찌 페이지
	@GetMapping(value="/bracelet")
	public String braceletPage() {
		return "/page/bracelet";
	}
	//시계 페이지
	@GetMapping(value="/watch")
	public String watchPage() {
		return "/page/watch";
	}
	//헤어용품 페이지
	@GetMapping(value="/hair")
	public String hairPage() {
		return "/page/hair";
	}
	//기타 페이지
	@GetMapping(value="/other")
	public String otherPage() {
		return "/page/other";
	}
}

















