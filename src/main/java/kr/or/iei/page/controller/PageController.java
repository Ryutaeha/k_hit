package kr.or.iei.page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.page.model.service.PageService;

@Controller
@RequestMapping(value="/page")
public class PageController {
	@Autowired
	private PageService pageService;
	
	@GetMapping(value="/search")
	public String searchPage() {
		return "page/search";
	}
	@GetMapping(value="/productSearch")
	public String productSearch(String productName) {
		//하고자 하는 행위 : 검색창에 input 하면 productName에 1글자라도 겹치면 count세서 
		//총 갯수,검색된 모든 product 목록화
		List productList = pageService.searchProduct(productName);
		return "/index";
		
	}
	
}
