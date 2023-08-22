package kr.or.iei.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/page")
public class PageController {
	
	@GetMapping(value="/search")
	public String searchPage() {
		return "page/search";
	}
	
	
}
