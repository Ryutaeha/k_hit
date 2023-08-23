package kr.or.iei.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.iei.seller.model.service.SellerService;
import kr.or.iei.seller.model.vo.ProductListData;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	//판매상품관리
	@GetMapping(value="/productManagement")
	public String productManagement(@SessionAttribute(required = false) Seller s, Model model, int reqPage) {
		ProductListData pld = sellerService.selectProductList(21,reqPage);
		model.addAttribute("productList", pld.getProductList());
		model.addAttribute("pageNavi", pld.getPageNavi());
		return "/seller/productManagement";
	}
	//판매등록 조회
	@GetMapping(value="/addNewProductList")
	public String addNewProductList() {
		return "/seller/addNewProductList";
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
	//판매상품 재고관리
	@GetMapping(value="/productStockManagement")
	public String productStockManagement() {
		return "/seller/productStockManagement";
	}
	//판매상품 수정폼
	@GetMapping(value="/updateProductFrm")
	public String updateProductFrm() {
		return "/seller/updateProductFrm";
	}
	//판매상품 수정2
	@PostMapping(value="/updateProduct")
	public String updateProduct() {
		return null;
	}
	//판매상품 등록폼
	@GetMapping(value="/addNewProductFrm")
	public String addNewProductFrm() {
		return "/seller/addNewProductFrm";
	}
	//판매상품 등록2
	@PostMapping(value="/addNewProduct")
	public String addNewProduct() {
		return null;
	}
}
