package kr.or.iei.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.product.model.vo.ReviewProduct;
import kr.or.iei.review.model.dao.ReviewDao;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewComment;
import kr.or.iei.review.model.vo.ReviewViewData;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	
	//리뷰 등록
	@Transactional
	public int insertReview(Review review) {
		int result = reviewDao.insertReview(review);
		return result;
	}


	public ReviewProduct selectReviewProduct(int orderNo) {
		ReviewProduct rp = reviewDao.selectReviewProduct(orderNo);
		return rp;
	}


	public int reviewTotalCount() {
		int totalCount = reviewDao.reviewTotalCount();
		return totalCount;
	}


	public List selectReviewList(int start, int end) {
		List reviewList = reviewDao.selectReviewList(start,end);
		return reviewList;
	}

	@Transactional
	public ReviewViewData selectOneReview(int reviewNo, int customerNo, int orderNo) {
		int result = reviewDao.updateReadCount(reviewNo);
		if(result>0) {
			Review r = reviewDao.selectOneReview(reviewNo,customerNo);
			ReviewProduct rp = reviewDao.selectReviewProduct(orderNo);
			List commentList = reviewDao.selectCommentList(reviewNo);
			List reCommentList = reviewDao.selectReCommentList(reviewNo);
			int reviewCount = reviewDao.selectReviewCount(reviewNo);
			ReviewViewData rvd = new ReviewViewData(r, rp, commentList, reCommentList, reviewCount);
			return rvd;
		}else {
			return null;			
		}
	}
	
	@ResponseBody
	@Transactional
	public int insertReviewLike(int reviewNo, int customerNo) {
		int result = reviewDao.insertReviewLike(reviewNo,customerNo);
		int likeCount = reviewDao.likeCount(reviewNo);
		return likeCount;
	}
	
	@ResponseBody
	@Transactional
	public int removeReviewLike(int reviewNo, int customerNo) {
		int result = reviewDao.removeReviewLike(reviewNo,customerNo);
		int likeCount = reviewDao.likeCount(reviewNo);
		return likeCount;
	}

	@Transactional
	public int insertComment(ReviewComment rc) {
		int result = reviewDao.insertComment(rc);
		return result;
	}

	@Transactional
	public int deleteComment(int reviewCommentNo) {
		return reviewDao.deleteComment(reviewCommentNo);
	}


}
