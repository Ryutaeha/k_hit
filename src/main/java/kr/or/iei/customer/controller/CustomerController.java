package kr.or.iei.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//회원가입
	@GetMapping(value = "/join")
	public String customerJoin() {
		return "/customer/join";
	}
	//장바구니
	@GetMapping(value="/cart")
	public String customerCart() {
		return "/customer/cart";
	}
	//결제하기
		@GetMapping(value="/payment")
		public String customerPayment() {
			return "/customer/payment";
		}
	//회원가입 완료
	@PostMapping(value="/joinComplete")
	public String joinComplete() {
		return "/customer/joinComplete";
	}
}
