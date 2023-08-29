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
		//리뷰카운트 조회
		
		
		
		model.addAttribute("searchCount", psd.getSearchCount());
		model.addAttribute("searchList", psd.getSearchList());
		model.addAttribute("reviewContent", psd.getReviewContent());
		System.out.println("검색한 단어 조회 : "+psd);
		return "/page/search";
		
	}
	//베스트 페이지
	@GetMapping(value="/best")
	public String bestPage() {
		List bestList = pageService.searchBest();
		return "/page/best";
	}
	//신상품 페이지
	@GetMapping(value="/new")
	public String newPage(Model model) {
		List newPrd = pageService.searchNewList();
		System.out.println("신상품 : "+newPrd);
		model.addAttribute("newPrd", newPrd);
		return "/page/new";
	}
	//모든 상품페이지
	@GetMapping(value="/all")
	public String allPage(Model model) {
		List allPrd = pageService.searchAll();
		model.addAttribute("allPrd", allPrd);
		return "/page/all";
	}
	//귀걸이 페이지
	@GetMapping(value="/earring")
	public String earringPage(Model model) {
		List list = pageService.searchEarring();
		model.addAttribute("list", list);
		return "/page/earring";
	}
	//목걸이 페이지
	@GetMapping(value="/necklace")
	public String necklacePage(Model model) {
		List list = pageService.searchNecklace();
		model.addAttribute("list", list);
		return "/page/necklace";
	}
	//반지 페이지
	@GetMapping(value="/ring")
	public String ringPage(Model model) {
		List list = pageService.searchRing();
		model.addAttribute("list", list);
		return "/page/ring";
	}
	//팔찌 페이지
	@GetMapping(value="/bracelet")
	public String braceletPage(Model model) {
		List list = pageService.searchBracelet();
		model.addAttribute("list", list);
		return "/page/bracelet";
	}
	//시계 페이지
	@GetMapping(value="/watch")
	public String watchPage(Model model) {
		List list = pageService.searchWatch();
		model.addAttribute("list", list);
		return "/page/watch";
	}
	//헤어용품 페이지
	@GetMapping(value="/hair")
	public String hairPage(Model model) {
		List list = pageService.searchHair();
		model.addAttribute("list", list);
		return "/page/hair";
	}
	//기타 페이지
	@GetMapping(value="/other")
	public String otherPage(Model model) {
		List list = pageService.searchOther();
		model.addAttribute("list", list);
		return "/page/other";
	}
}

















