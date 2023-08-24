package kr.or.iei.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.customer.model.service.CustomerService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired 
	private CustomerService customerService;
	
	//리뷰 작성/수정
	@GetMapping("/reviewWrite")
	public String reviewWrtie() {
		return "review/reviewWriteFrm"; //경로 나중에 수정하기
	}
}
