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
		//하고자 하는 행위 : 검색창에 input 하면 productName에 1글자라도 겹치면 count세서 
		//총 갯수,검색된 모든 product 목록화
		PageSearchDate psd = pageService.searchProduct(searchWord);
		model.addAttribute("searchCount", psd.getSearchCount());
		model.addAttribute("searchList", psd.getSearchList());
		//리뷰카운트 조회
		//System.out.println("검색한 단어 조회 : "+psd);
		return "/page/search";
		
	}
	
}
