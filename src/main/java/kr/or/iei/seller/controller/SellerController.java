package kr.or.iei.seller.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.seller.model.service.SellerService;
import kr.or.iei.seller.model.vo.ProductListData;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
	private String root;
	
	@GetMapping(value="/mypage")
	public String mypage() {
		return "/seller/mypage";
	}
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
	//판매자 회원가입창으로 넘어가기
	@GetMapping(value="/join")
	public String joinPage() {
		return "/seller/join";
	}
	//회원가입 완료했을시 넘어가기
	@PostMapping(value="/joinComplete")
	public String joinComplete(Seller s,String customerEmail2,MultipartFile imgFile) {
		System.out.println("s : "+s);
		System.out.println("imgFIle :"+imgFile);
		//이미지파일 경로 설정
		String savepath = root+"seller/";
		String filepath = fileUtil.getFilepath(savepath, imgFile.getOriginalFilename());
		s.setSellerImg(filepath);
		System.out.println("sellerImg :"+s.getSellerImg());
		File upFile = new File(savepath+filepath);
		try {
			imgFile.transferTo(upFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = sellerService.insertSeller(s,customerEmail2);
		if(result>0) {
			//회원가입성공시
			return "/seller/joinComplete";			
		}else {
			//회원가입실패시
			return "/";
		}
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
	
	//로그인
	@PostMapping(value="signin")
	public String signIn(String sellerSignId, String sellerSignPw,HttpSession session, Model model) {
		Seller s = sellerService.selectOneSeller(sellerSignId,sellerSignPw);
		if(s != null) {
			//로그인한 판매자 회원 정보 저장
			session.setAttribute("s", s);
			
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
