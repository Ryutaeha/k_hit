package kr.or.iei.customer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "customer/join";
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

	//회원가입 confirm
	
		
	//회원가입
	@PostMapping(value="/joinComplete")
	public String joinComplete(Customer customer, String customerEmail2) {
		int result = customerService.insertCustomer(customer,customerEmail2);
		if(result>0) {
			return "customer/joinComplete";			
		}else {
			return "redirect:/"; //메인페이지 (모달)
		}
	}
	
	//회원가입 로그인 중복확인
	@ResponseBody
	@GetMapping(value="/checkId")
	public String checkId(String customerId) {
		Customer c = customerService.selectCustomerId(customerId);
		if(c == null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	//로그인
	@PostMapping(value="/signin")
	public String signIn(String customerSignId, String customerSignPw,HttpSession session, Model model) {
		Customer c = customerService.selectOneCustomer(customerSignId,customerSignPw);
		if(c != null) {
			//로그인한 고객 회원 정보 저장
			session.setAttribute("c", c);
			
			model.addAttribute("title","로그인 성공");
			model.addAttribute("msg", "환영합니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/");
		}else {
			model.addAttribute("title","로그인 실패");
			model.addAttribute("msg", "아이디/비밀번호를 확인해 주세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/common/login");
		}
		return "common/msg";
		
	}
	
	//로그아웃 
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
















