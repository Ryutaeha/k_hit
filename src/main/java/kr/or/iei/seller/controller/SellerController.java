package kr.or.iei.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.seller.model.service.SellerService;

@Controller
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;	
	
	@GetMapping(value="/productManagement")
	public String productManagement() {
		return "/seller/productManagement";
	}
	
	@GetMapping(value="/addNewProduct")
	public String addNewProduct() {
		return "/seller/addNewProduct";
	}
	//판매자 회원가입
	@GetMapping(value="/join")
	public String joinPage() {
		return "/seller/join";
	}
	//회원가입 완료
	@PostMapping(value="/joinComplete")
	public String joinComplete() {
		return "/seller/joinComplete";
	}
}
