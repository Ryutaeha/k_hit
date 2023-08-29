package kr.or.iei.customer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.iei.customer.model.service.CustomerService;
import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.WishListData;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	//회원가입 confirm
	@GetMapping(value = "/joinConfirm")
	public String joinConfirm(Model model) {
		model.addAttribute("title","회원가입");
		model.addAttribute("msg", "[고객] 회원가입이 맞으신가요?");
		model.addAttribute("icon", "question");
		model.addAttribute("loc", "/customer/join");
		model.addAttribute("cancelLoc", "/common/login");
		
		return "common/confirmMsg";
	}
	
	//회원가입 페이지 이동
	@GetMapping(value = "/join")
	public String customerJoin() {
		return "customer/join";
	}
	
	//장바구니 페이지 이동 (로그인 x > 로그인 페이지로 이동)
	@GetMapping(value="/cart")
	public String Cart(String customerSignId, String customerSignPw, HttpSession session,Model model) {
//		Customer c = customerService.selectOneCustomer(customerSignId,customerSignPw);
//		if(c == null) {
//			model.addAttribute("title","접근 실패");
//			model.addAttribute("msg", "로그인 후 이용해 주세요.");
//			model.addAttribute("icon", "error");
//			model.addAttribute("loc", "/common/login");
//
//		}else if(c != null){
//			session.setAttribute("c", c);
//			
//			return "customer/cart";
//	
//		}
//		return "common/msg";
		return "customer/cart";
	}
	
	//결제하기
	@GetMapping(value="/payment")
	public String customerPayment() {
		return "/customer/payment";
	}
	
	//마이페이지 주문 내역 목록 확인
	@GetMapping(value="/orderList")
	public String orderList() {
		return "/customer/orderList";
	}
	
	//고객 취소/환불 목록 페이지
	@GetMapping(value="/cancelRefundList")
	public String refundList() {
		return "/customer/cancelRefundList";
	}
	
	//취소 신청 페이지
	@GetMapping(value="/cancel")
	public String cancel() {
		return "/customer/cancel";
	}

	
	//찜목록
	@GetMapping(value="/wishList") 
	public String wishList(Customer c, Model model, int reqPage){
		WishListData wld = customerService.selectWishList(c.getCustomerNo(),reqPage);
		return "customer/wishList";
	}

	
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
	
	//마이페이지 이동
	@GetMapping(value = "/mypage")
	public String customerMypage() {
		return "customer/mypage";
	}
	
	//마이페이지 회원정보관리페이지
	@GetMapping(value="/myInfo")
	public String customerMyInfo(@SessionAttribute(required = false) Customer c, Model model) {
		/*
		String customerEmail = c.getCustomerEmail().substring(0,c.getCustomerEmail().lastIndexOf("@"));
		String customerEmail2 = c.getCustomerEmail().substring(c.getCustomerEmail().lastIndexOf("@")+1);
		model.addAttribute("customerEmail", customerEmail);
		model.addAttribute("customerEmail2", customerEmail2);
		*/
		//월요일 강사님께
		return "customer/myInfo";
	}
	
	//고객 정보수정
	@PostMapping(value="/update")
	public String updateCustomer(String customerEmail2,Customer customer,Model model,@SessionAttribute(required = false) Customer c) {
		int result = customerService.updateCustomer(customerEmail2,customer);
		if(result>0){
			c.setCustomerEmail(customer.getCustomerEmail());
			c.setCustomerName(customer.getCustomerName());
			c.setCustomerPhone(customer.getCustomerPhone());
			c.setCustomerPw(customer.getCustomerPw());
			return "customer/updateSuccess";
		}else {
			model.addAttribute("title", "회원 정보 수정 실패");
			model.addAttribute("msg", "정보 수정에 실패하셨습니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	//회원탈퇴 여부 확인
	@GetMapping(value="/unregister")
	public String unregisterCustomer(@SessionAttribute(required = false)Customer c,Model model) {
		model.addAttribute("title","회원탈퇴");
		model.addAttribute("msg", "[ "+c.getCustomerId()+" 님 ] 정말 탈퇴하시겠습니까?");
		model.addAttribute("icon", "question");
		model.addAttribute("loc", "/customer/delete");
		model.addAttribute("cancelLoc", "/customer/mypage");
		return "common/confirmMsg";
	}
	
	//회원 탈퇴
	@GetMapping(value="/delete")
	public String deleteCustomer(@SessionAttribute(required = false)Customer c, Model model) {
		int result = customerService.deleteCustomer(c.getCustomerNo());
		if(result>0) {
			model.addAttribute("title", "탈퇴완료");
			model.addAttribute("msg", "그동안 이용해 주셔서 감사합니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/customer/logout");
			return "common/msg";
		}else {
			model.addAttribute("title", "회원탈퇴");
			model.addAttribute("msg", "회원탈퇴를 실패했습니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/cusotmer/mypage");
			return "common/msg";
		}
	}
}
















