package kr.or.iei.review.controller;

import java.io.File;
import java.io.IOException;

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
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.Review;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired 
	private ReviewService reviewService;
	@Autowired
	private FileUtil fileUtil;
	@Value("${file.root}")
	private String root;
	
	//리뷰 작성/수정 페이지 이동
	@GetMapping("/reviewWriteFrm") //주문내역 넘어오는 것 보고 수정하기
	public String reviewWrtieFrm(Model model) {
		
		return "review/reviewWriteFrm";
	}
	//리뷰 작성 
	@PostMapping("/reviewWrite")
	public String reviewWrtie(Review r,MultipartFile imageFile, Model model) {
		String savepath = root+"photo/";
		String filepath = fileUtil.getFilepath(savepath, imageFile.getOriginalFilename());
		r.setFilepath(filepath);
		File upFile = new File(savepath+filepath);
		try {
			imageFile.transferTo(upFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = reviewService.insertReview(r);
		if(result>0) {
			model.addAttribute("title","REVIEW");
			model.addAttribute("msg", "후기 등록 완료");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/review/reviewView");
		}else {
			model.addAttribute("title","REVIEW");
			model.addAttribute("msg", "후기 등록 실패");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/review/reviewWriteFrm");
		}
		return "common/msg";
	}
	
	//리뷰 상세 보기
	@GetMapping("/reviewView")
	public String reviewVeiw() {
		
		return "review/reviewView";//경로 나중에 수정하기
	}
	
	//리뷰게시판
	@GetMapping("/reviewList")
	public String reviewList() {
		return "review/reviewList";
	}
	
}
