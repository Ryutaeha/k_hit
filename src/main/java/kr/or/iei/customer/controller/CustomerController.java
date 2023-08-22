package kr.or.iei.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.customer.model.service.CustomerService;
import kr.or.iei.customer.model.vo.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	//회원가입 페이지 이동
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


	//회원가입
	@PostMapping(value="/joinComplete")
	public String joinComplete(Customer customer) {
		int result = customerService.insertCustomer(customer);
		if(result>0) {
			return "/customer/joinComplete";			
		}else {
			return "/";
		}
	}
}
