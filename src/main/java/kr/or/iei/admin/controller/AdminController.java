package kr.or.iei.admin.controller;

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
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.ProductDetail;
import kr.or.iei.seller.model.vo.Seller;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value = "/adminLogin")
	public String adminLogin() {
		return "/admin/adminLogin";
	}
	@PostMapping(value = "/login")
	public String login(String adminSignId, String adminSignPw, Model model,HttpSession session) {
		Admin a = adminService.AdminLogin(adminSignId,adminSignPw);
		if(a!= null) {
			session.setAttribute("a", a);
			model.addAttribute("title","로그인 성공");
			model.addAttribute("msg", "환영합니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/adminIndex");		
		}else {
			model.addAttribute("title","로그인 실패");
			model.addAttribute("msg", "아이디/비밀번호를 확인해 주세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/adminLogin");
		}
		return "common/msg";
	}
	@GetMapping(value = "/adminIndex")
	public String adminIndex() {
		return "/admin/adminIndex";
	}

	@GetMapping(value = "/question")
	public String question() {
		return "/admin/question";
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
	@ResponseBody
	@PostMapping(value="/nContent")
	public Notice nContent(int nNo){
		return adminService.noticeView(nNo);
	}
	@GetMapping(value="/noticeList")
	public String notice(Model model, int noticeFix,String input){
		List<Notice> list = adminService.noticeList(noticeFix,input);			
		model.addAttribute("list", list);
		return "/admin/notice";
	}

	@GetMapping(value="/noticeFrm")
	public String noticeFrm(){
		return "/admin/noticeFrm";
	}
	
	@ResponseBody
	@PostMapping(value = "/noticeFile",produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"notice/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/"+filepath;
	}
	@PostMapping(value = "/write")
	public String noticeWrite(Notice n ,Model model) {
		int result = adminService.insertNotice(n);
		return "/admin/noticeFrm";
	}
	@PostMapping(value="/productCheckChange")
	public String productCheckChange(int productCheck,int productNo,Model model){
		System.out.println(productCheck+"   "+productNo);
		int result = adminService.productCheckChange(productCheck,productNo);
		if(result == 1) {
			model.addAttribute("title","수정 성공");
			model.addAttribute("msg", "상품 수정에 성공하셨습니다");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/product?categoryNo=0&input=&productCheck="+productCheck);		
		}else {
			model.addAttribute("title","수정 실패");
			model.addAttribute("msg", "메인 페이지로 돌아갑니다 잠시후 다시 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/adminIndex");
		}
		return "common/msg";
	}
}