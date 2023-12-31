package kr.or.iei.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
			model.addAttribute("loc", "/admin/member?memberCode=1&input=");		
		}else {
			model.addAttribute("title","로그인 실패");
			model.addAttribute("msg", "아이디/비밀번호를 확인해 주세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/adminLogin");
		}
		return "common/msg";
	}
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
//		현재세션에 저장되어 있는 정보 파기
		session.invalidate();
		return "/admin/adminLogin";
	}

	
	@GetMapping(value = "/test")
	public String test(Model model) {
		List list = adminService.salesList();
		model.addAttribute("list",list);
		return "/admin/test";
	}

	@GetMapping(value = "/question")
	public String question(Model model) {
		List list = adminService.question();
		model.addAttribute("qna",list);
		return "/admin/question";
	}
	
	@GetMapping(value = "/questionSearch")
	public String questionSearch(Model model,String input) {
		List list = adminService.question(input);
		System.out.println(input);
		System.out.println(list);
		model.addAttribute("qna",list);
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
	public String sales(Model model){
		List list = adminService.salesList();
		model.addAttribute("list",list);
		return "/admin/sales";
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
	@PostMapping(value="/pContentO")
	public List pContentO(int pNo){
		return adminService.productO(pNo);
	}
	@ResponseBody
	@PostMapping(value="/sContent")
	public Seller sContent(String sId){
		return adminService.selectSeller(sId);
	}
	@ResponseBody
	@PostMapping(value="/sContentP")
	public List sContentP(String sId){
		return adminService.selectSellerP(sId);
	}
	
	@ResponseBody
	@PostMapping(value="/cContent")
	public Customer cContent(String cId){
		return adminService.selectCustomer(cId);
	}
	@ResponseBody
	@PostMapping(value="/cContentB")
	public List cContentB(String cId){
		return adminService.selectCustomerB(cId);
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
		if(result!=0) {
			model.addAttribute("title", "작성완료");
			model.addAttribute("msg", "공지사항 처리완료");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}else {
			model.addAttribute("title", "작성실패");
			model.addAttribute("msg", "잠시후 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}
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
	@ResponseBody
	@PostMapping(value = "/fix")
	public int fix(int fix, int nNo) {
		return adminService.fix(fix,nNo);
	}
	@ResponseBody
	@PostMapping(value = "/test1")
	public List test() {
		return adminService.salesListTest();
	}
	@ResponseBody
	@PostMapping(value="/qContent")
	public List qContent(String qNo){
		return adminService.selectQna(qNo);
	}
	@ResponseBody
	@PostMapping(value="/qContentC")
	public List qContentC(String qNo){
	return adminService.selectQnaC(qNo);
	}
	@GetMapping(value = "/adminMsg")
	public String adminMsg(Model model) {
		model.addAttribute("title", "관리자 페이지");
		model.addAttribute("msg", "로그인 후 이용하세요");
		model.addAttribute("icon", "warning");
		model.addAttribute("loc", "/admin/adminLogin");
		return "common/msg";
	}
	
	@PostMapping(value="/qnaAnswer")
	public String qnaAnswer(int qnaNo, String qnaAnswerComment, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin a = (Admin)session.getAttribute("a");
		String adminId =a.getAdminId();
		System.out.println(qnaNo+qnaAnswerComment+adminId);
		int result = adminService.qnaAnswer(qnaNo,qnaAnswerComment,adminId);
		if(result!=0) {
			model.addAttribute("title", "작성완료");
			model.addAttribute("msg", "문의 사항 처리 완료");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/question");
			return "common/msg";
		}else {
			model.addAttribute("title", "작성실패");
			model.addAttribute("msg", "잠시후 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/question");
			return "common/msg";
		}
	}
	
	@PostMapping(value = "/noticeDel")
	public String noticeDel(int noticeNo,Model model) {
		int result = adminService.noticeDel(noticeNo);
		if(result!=0) {
			model.addAttribute("title", "삭제완료");
			model.addAttribute("msg", "공지사항 삭제 완료");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}else {
			model.addAttribute("title", "삭제실패");
			model.addAttribute("msg", "잠시후 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}
	}
	@PostMapping(value = "/qnaAnswerDel")
	public String qnaAnswerDel(int qnaCommentNo,Model model) {
		int result = adminService.qnaAnswerDel(qnaCommentNo);
		if(result!=0) {
			model.addAttribute("title", "삭제완료");
			model.addAttribute("msg", "공지사항 삭제 완료");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}else {
			model.addAttribute("title", "삭제실패");
			model.addAttribute("msg", "잠시후 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/noticeList?noticeFix=2&input=");
			return "common/msg";
		}
	}
	
	@PostMapping(value = "/modifyGo")
	public String modifyGo(int pw, String phone,Model model,HttpSession session,HttpServletRequest request) {
		session = request.getSession();
		Admin a = (Admin)session.getAttribute("a");
		int result = adminService.modifyGo(pw,phone,a.getAdminId());
		if(result!=0) {
			session.invalidate();
			model.addAttribute("title", "수정완료");
			model.addAttribute("msg", "수정 완료 다시 로그인 하세요");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/admin/adminLogin");
			return "common/msg";
		}else {
			model.addAttribute("title", "수정실패");
			model.addAttribute("msg", "잠시후 시도해주세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/admin/admin/member?memberCode=1&input=");
			return "common/msg";
		}
	}
	
	
}