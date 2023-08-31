package kr.or.iei.seller.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.AddNewProduct;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductOptionListData;
import kr.or.iei.seller.model.service.SellerService;
import kr.or.iei.seller.model.vo.CancelRefundData;
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
	//판매자 회원가입 Confirm
	@GetMapping(value="/joinConfirm")
	public String joinPage(Model model) {
		model.addAttribute("title","회원가입");
		model.addAttribute("msg", "[판매자] 회원가입이 맞으신가요?");
		model.addAttribute("icon", "question");
		model.addAttribute("loc", "/seller/join");
		model.addAttribute("cancelLoc", "/common/login");
		return "common/confirmMsg";
	}
	
	//회원가입 페이지 이동
	@GetMapping(value="/join")
	public String sellerJoin() {
		return "seller/join";
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
	public String addNewProduct(Product p, String[] optionColor, String[] optionSize, @SessionAttribute(required = false) Seller s,MultipartFile upfile, Model model) {
		//sysout으로 (상품명,판매가,카테고리,판매자번호,파일,요약,상셍)
		//sysout으로 옵션 사이즈1, 옵션 색상 1
		//sysout으로 옵션 사이즈1, 옵션 색상 1
		//sysout으로 옵션 사이즈1, 옵션 색상 1
		//sysout으로 옵션 사이즈1, 옵션 색상 1
		
		//p.setProductImg(upfile);
		//String productImg = new productImg(upfile);
		
		//1. product테이블에 insert
		String savepath = root+"product/";
		String filepath = fileUtil.getFilepath(savepath, upfile.getOriginalFilename());
		p.setProductImg(filepath);
		
		File uploadFile = new File(savepath+filepath);
		try {
			upfile.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(p.getProductName());
		System.out.println(p.getProductPrice());
		System.out.println(p.getCategoryNo());
		System.out.println(s.getSellerNo());
		System.out.println(p.getProductImg());
		System.out.println(p.getProductContent());
		System.out.println(p.getProductContentDetails());
		System.out.println(p.getProductCheck());
		
		for(String size : optionSize) {
			System.out.println(size);
		}
		for(String color : optionColor) {
			System.out.println(color);
		}
		//서비스 	-> 1. product테이블에 insert
		//		-> 2. product테이블에 방금 insert한 productNo 조회
		//		-> 3. optionColor배열에서 1개꺼내고, optionSize배열에서 1개
		
		int result = sellerService.addNewProduct(p, s.getSellerNo(), optionSize, optionColor);
		
		if(result>0) {
			model.addAttribute("title", "상품 등록 성공");
			model.addAttribute("msg", "등록한 상품이 검수중입니다.");
			model.addAttribute("icon", "success");
			
			
			//2. product테이블에 방금 insert한 productNo 조회
			int productNo = p.getProductNo();
			System.out.println(p.getProductNo());
			System.out.println(result);
			
			
			
		}else {
			model.addAttribute("title", "상품 등록 실패");
			model.addAttribute("msg", "잠시 후 다시 시도하세요.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/seller/addNewProductList?reqPage=1");
		return "common/msg";
		
		
		
		/*
		for(int i=0;i<optionColor.length;i++) {
			ProductOption productOption = new ProductOption();
			productOption.setOptionColor(optionColor[i]);
			productOption.setOptionSize(optionSize[i]);
			//productOption.setProductNo(2에서 조회한 번호);
			//insert
		}
		*/
		
		//insert성공조건 옵션갯수+1개
		
		//return "redirect:/";
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
	public String concelRefundPage(Model model,@SessionAttribute(required = false) Seller s ) {
		
		CancelRefundData crd = sellerService.cancelList(s);
		//System.out.println("list : "+list);
		System.out.println(crd.getCancelList());
		System.out.println(crd.getRefundList());
		model.addAttribute("cancelList", crd.getCancelList());
		model.addAttribute("refundList", crd.getRefundList());
		return "/seller/cancelRefund";
	}
	//환불내역페이지
	
	//취소확인
	@GetMapping(value="/cancelOrder")
	public String cancelOrder() {
		return null;
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
	public String changeOptionStock(int optionStock, int productOptionNo, Model model) {
		int result = sellerService.changeOptionStock(optionStock, productOptionNo);
		if(result>0) {
			return "0";
		}else {
			return "1";
		}
	}

	
	/*
	@ResponseBody
	@PostMapping(value="/editor",produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
			//System.out.println(image);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "/editor/"+filepath;
	}
	*/
	
	//판매자 리뷰 확인 
	@GetMapping(value="/review")
	public String sellerReview(@SessionAttribute(required = false) Seller s,Model model) {
		int sellerNo = s.getSellerNo();
		int totalCount = sellerService.reviewTotalCount(sellerNo);
		model.addAttribute("totalCount",totalCount);
		return "seller/sellerReview";
	}
	
	//판매자리뷰 더보기
	@ResponseBody
	@PostMapping(value="more")
	public List more(@SessionAttribute(required = false)Seller s, int start, int end) {
		int sellerNo = s.getSellerNo();
		List reviewList = sellerService.sellerReviewList(sellerNo,start,end);
		return reviewList;
	}
	
	//판매자 회원가입 로그인 중복확인
	@ResponseBody
	@GetMapping(value="/checkId")
	public String checkId(String sellerId) {
		Seller s = sellerService.selectSellerId(sellerId);
		if(s == null) {
			return "0";
		}else {
			return "1";
		}
	}
	

	//고객 아이디/비번 찾기 확인
	@GetMapping(value = "/searchConfirm")
	public String searchConfirm(Model model) {
		model.addAttribute("title","아이디/비밀번호 찾기");
		model.addAttribute("msg", "[판매자] 아이디/비밀번호찾기 맞나요?");
		model.addAttribute("icon", "question");
		model.addAttribute("loc", "/seller/searchIdPwFrm");
		model.addAttribute("cancelLoc", "/common/login");
			
		return "common/confirmMsg";
	}
		
		@GetMapping(value = "/searchIdPwFrm")
		public String searchIdPwFrm(){
			return "seller/searchIdPwFrm";
		}
	@GetMapping(value = "/deleteSeller")
	public String deleteSeller(@SessionAttribute(required = false)Seller s,Model model ) {
		int result = sellerService.deleteSeller(s);
		if(result>0) {
			model.addAttribute("title", "탈퇴완료");
			model.addAttribute("msg", "그동안 이용해 주셔서 감사합니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/seller/logout");
			return "common/msg";
		}else {
			model.addAttribute("title", "회원탈퇴");
			model.addAttribute("msg", "회원탈퇴를 실패했습니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/seller/myinfo");
			return "common/msg";
		}
	}

	//취소요청확인
	@GetMapping(value = "/cancelPrd")
	public String cancelPrd(int orderNo) {
		int result = sellerService.cancelPrd(orderNo);
		return "/";

	}
	//판매자 판매내역
	@GetMapping(value="/selling")
	public String selling(@SessionAttribute(required = false)Seller s,Model model) {
		List selling = sellerService.selectSelling(s.getSellerNo());
		model.addAttribute("sellingList", selling);
		return "seller/selling";
		

	}
}


