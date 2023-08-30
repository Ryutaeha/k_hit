package kr.or.iei;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.iei.page.model.service.PageService;

@Controller
public class HomeController {
	@Autowired
	private PageService pageService;
	@GetMapping(value="/")
	public String main(Model model) {
		List newPrd = pageService.searchNewListFive();
		model.addAttribute("newPrd", newPrd);
		return "index";
	}
	/*
	@GetMapping(value = "/")
	public String mainbest(Model model) {
		List newPrd = pageService.searchBestListFive();
		return "index";
	}
	*/
	
	@GetMapping(value="/ref")
	public String ref() {
		return "ref";
	}
}
