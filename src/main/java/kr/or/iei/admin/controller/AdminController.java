package kr.or.iei.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.ProductDetail;
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
	public String member(int memberCode,String input, Model model){
			if(memberCode == 1) {
				List<Customer> list = adminService.customerList(input);	
				model.addAttribute("list", list);
			}else if(memberCode == 2){
				List<Seller> list = adminService.sellerList(input);
				model.addAttribute("list", list);
			}
			model.addAttribute("code",memberCode);
		return "/admin/member";
	}
	
	@GetMapping(value="/banner")
	public String banner(){
		return "/admin/banner";
	}

	@GetMapping(value="/product")
	public String product(Model model, String input, int categoryNo, int productCheck){
		List<Category> category = adminService.category();
		List<ProductDetail> product = adminService.product(input,categoryNo,productCheck);
		model.addAttribute("productCheck",productCheck);
		model.addAttribute("category",category);
		model.addAttribute("product",product);
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
	@ResponseBody
	@PostMapping(value="/pContent")
	public List pContent(int pNo){
		List<ProductDetail> product = adminService.product(pNo);
		return product;
	}
	@ResponseBody
	@PostMapping(value="/sContent")
	public Seller sContent(String sId){
		return adminService.selectSeller(sId);
	}
	@ResponseBody
	@PostMapping(value="/cContent")
	public Customer cContent(String cId){
		return adminService.selectCustomer(cId);
	}
}