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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.product.model.vo.ProductOptionListData;
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
	
	@GetMapping(value="/selling")
	public String sellingPage() {
		return "seller/selling";
	}
	//판매상품관리
	@GetMapping(value="/productManagement")
	public String productManagement(@SessionAttribute(required = false) Seller s, Model model, int reqPage) {
		ProductListData pld = sellerService.selectProductList(s.getSellerNo(),reqPage);
		model.addAttribute("productList", pld.getProductList());
		model.addAttribute("pageNavi", pld.getPageNavi());
		return "/seller/productManagement";
	}
	//판매등록 조회
	@GetMapping(value="/addNewProductList")
	public String addNewProductList(@SessionAttribute(required = false) Seller s, Model model, int reqPage) {
		ProductListData pld = sellerService.addNewProductList(s.getSellerNo(),reqPage);
		model.addAttribute("productList", pld.getProductList());
		model.addAttribute("pageNavi", pld.getPageNavi());		
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
	public String productStockManagement(@SessionAttribute(required = false) Seller s, Model model, int reqPage){
		System.out.println(s);
		ProductOptionListData pold = sellerService.productStockManagement(s.getSellerNo(),reqPage);
		//System.out.println(pold);
		model.addAttribute("prductOptionList", pold.getProductOptionList());
		model.addAttribute("pageNavi", pold.getPageNavi());
		return "seller/productStockManagement";
	}
	//판매상품 수정폼
	@GetMapping(value="/updateProductFrm")
	public String updateProductFrm() {
		return "seller/updateProductFrm";
	}
	//판매상품 수정2
	@PostMapping(value="/updateProduct")
	public String updateProduct() {
		return null;
	}
	//판매상품 등록폼
	@GetMapping(value="/addNewProductFrmEditor")
	public String addNewProductFrmEditor() {
		return "seller/addNewProductFrmEditor";
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
	//취소환불내역 페이지이동
	@GetMapping(value="/cancelRefund")
	public String concelRefundPage() {
		return "/seller/cancelRefund";
	}
	//판매자 정보수정페이지이동
	@GetMapping(value="/myInfo")
	public String myInfoPage() {
		return "/seller/myInfo";
	}
	//판매자 정보수정
	@PostMapping(value="/update")
	public String updateSeller(String customerEmail2,MultipartFile imgFile, Seller s,Model model) {
		System.out.println("이메일 뒷 : "+customerEmail2);
		System.out.println("판매자 : "+s);
		System.out.println("변경 할 대표이미지 : "+imgFile);
		
			String savepath = root+"seller/";
			String filepath = fileUtil.getFilepath(savepath, imgFile.getOriginalFilename());
			s.setSellerImg(filepath);
			File upFile = new File(savepath+filepath);
			try {
				imgFile.transferTo(upFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			int result = sellerService.updateSeller(customerEmail2,s);
			if(result>0){
				return "/seller/updateSuccess";
			}else {				
				model.addAttribute("title", "회원 정보 수정 실패");
				model.addAttribute("msg", "정보 수정에 실패하셨습니다.");
				model.addAttribute("icon", "error");
				model.addAttribute("loc", "/");
				return "common/msg";
			}
	}
	//판매상품 재고관리페이지에서 재고수정
	@ResponseBody
	@GetMapping(value="/changeOptionStock")
	public int changeOptionStock(int optionStock, int productOptionNo, Model model) {
		int result = sellerService.changeOptionStock(optionStock, productOptionNo);
		return result;
	}
}	
	/*
	@ResponseBody
	@PostMapping(value="/editor",produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"seller/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		
		
	}
	*/

