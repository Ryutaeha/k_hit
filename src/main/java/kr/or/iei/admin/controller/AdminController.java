package kr.or.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.customer.model.vo.CustomerVo;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping(value = "/adminIndex")
	public String adminIndex() {
		return "/admin/adminIndex";
	}
	@GetMapping(value = "/question")
	public String question() {
		return "/admin/question";
	}
	@GetMapping(value="/notice")
	public String notice(){
		return "/admin/notice";
	}
	@GetMapping(value="/noticeFrm")
	public String noticeFrm(){
		return "/admin/noticeFrm";
	}
		@GetMapping(value="/member")
	public String member(int memberCode, Model model){
			if(memberCode == 1) {
				List<CustomerVo> list = adminService.memberList(memberCode);				
			}else if(memberCode == 2){
				
			}
		return "/admin/member";
	}
		@GetMapping(value="/banner")
	public String banner(){
		return "/admin/banner";
	}
		@GetMapping(value="/product")
	public String product(){
		return "/admin/product";
	}
		@GetMapping(value="/sales")
	public String sales(){
		return "/admin/sales";
	}
		@GetMapping(value="/income")
	public String income(){
		return "/admin/income";
	}
		@GetMapping(value="/consumption")
	public String consumption(){
		return "/admin/consumption";
	}
		@GetMapping(value="/modify")
	public String modify(){
		return "/admin/modify";
	}	
}

