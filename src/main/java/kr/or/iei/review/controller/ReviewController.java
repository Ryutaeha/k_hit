package kr.or.iei.review.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.Order;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ReviewProduct;
import kr.or.iei.review.model.service.ReviewService;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewComment;
import kr.or.iei.review.model.vo.ReviewViewData;

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
	@GetMapping("/reviewWriteFrm")
	public String reviewWrtieFrm(int orderNo, Model model) { //개별상품번호 받은 후 넣기!!
		ReviewProduct rp = reviewService.selectReviewProduct(orderNo);
		model.addAttribute("rp",rp);
		return "review/reviewWriteFrm";
	}
	//리뷰 작성 
	@PostMapping("/write")
	public String reviewWrtie(Review r,MultipartFile imageFile, Model model) {
		String savepath = root+"review/";
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
			model.addAttribute("loc", "/review/reviewList");
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
	public String reviewVeiw(int reviewNo, @SessionAttribute(required = false)Customer c, Model model) {
		int customerNo = (c == null) ? 0 : c.getCustomerNo();
		int orderNo = reviewService.selectReviewOrderNO(reviewNo);
		ReviewViewData rvd = reviewService.selectOneReview(reviewNo,customerNo,orderNo);
		if(rvd != null) {
			model.addAttribute("r", rvd.getR());
			model.addAttribute("rp", rvd.getRp());
			model.addAttribute("comment", rvd.getCommentList());
			model.addAttribute("reComment", rvd.getReCommentList());
			model.addAttribute("reviewCount",rvd.getReviewCount());
			return "review/reviewView";
		}else {
			model.addAttribute("title", "후기 조회 실패");
			model.addAttribute("msg", "이미 삭제된 게시물입니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/review/reviewList");
			return "common/msg";
		}
	}
	
	//리뷰게시판
	@GetMapping("/reviewList")
	public String reviewList(Model model) {
		
		int totalCount = reviewService.reviewTotalCount();
		model.addAttribute("totalCount", totalCount);
		return "review/reviewList";
	}
	
	//리뷰게시판 더보기
	@ResponseBody
	@PostMapping(value="/more")
	public List more(int start, int end) {
		List reviewList = reviewService.selectReviewList(start,end);
		return reviewList;
	}
	//리뷰 좋아요 
	@ResponseBody
	@PostMapping(value="/addLike")
	public int addLike(int reviewNo, int customerNo) {
		int likeCount = reviewService.insertReviewLike(reviewNo,customerNo);
		return likeCount;
	}
	//리뷰 좋아요 삭제
	@ResponseBody
	@PostMapping(value="/removeLike")
	public int removeLike(int reviewNo, int customerNo) {
		int likeCount = reviewService.removeReviewLike(reviewNo,customerNo);
		return likeCount;
	}
	//리뷰 등록
	@PostMapping(value="/insertComment")
	public String insertComment(ReviewComment rc, Model model) {
		int result = reviewService.insertComment(rc);
		if(result>0) {
			model.addAttribute("title", "등록완료");
			model.addAttribute("msg", "댓글이 등록되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "등록실패");
			model.addAttribute("msg", "댓글이 등록에 실패했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/review/reviewView?reviewNo="+rc.getReviewRef());
		return "common/msg";
	}
	//리뷰댓글삭제
	@GetMapping(value="/deleteComment")
	public String deleteComment(int reviewCommentNo, int reviewNo, Model model) {
		int result = reviewService.deleteComment(reviewCommentNo);
		if(result>0) {
			model.addAttribute("title", "삭제성공");
			model.addAttribute("msg", "댓글이 삭제되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "삭제실패");
			model.addAttribute("msg", "댓글 삭제에 실패했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc","/review/reviewView?reviewNo="+reviewNo); //경로 바꿔주기
		return "common/msg";
	}
	
	//리뷰 수정
	@PostMapping(value="/updateComment")
	public String updateComment(ReviewComment rc, Model model) {
		int result = reviewService.updateComment(rc);
		System.out.println(rc);
		if(result>0) {
			model.addAttribute("title", "수정완료");
			model.addAttribute("msg", "댓글이 수정되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "수정실패");
			model.addAttribute("msg", "댓글 수정에 실패했습니다.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/review/reviewView?reviewNo="+rc.getReviewRef());
		return "common/msg";
	}
	
}
