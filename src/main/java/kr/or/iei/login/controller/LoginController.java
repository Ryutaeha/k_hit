package kr.or.iei.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class LoginController {
	
	@GetMapping(value = "/login")
	public String adminIndex() {
		return "/common/login";
	}
}
