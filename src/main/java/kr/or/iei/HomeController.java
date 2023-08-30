package kr.or.iei;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.iei.page.model.service.PageService;
import kr.or.iei.page.model.vo.MainListData;

@Controller
public class HomeController {
	@Autowired
	private PageService pageService;
	@GetMapping(value="/")
	public String main(Model model) {
		MainListData mld = pageService.searchNewListFive();
		model.addAttribute("newList", mld.getNewList());
		model.addAttribute("bestList", mld.getBestList());
		return "index";
	}
	
	
	
	@GetMapping(value="/ref")
	public String ref() {
		return "ref";
	}
}
