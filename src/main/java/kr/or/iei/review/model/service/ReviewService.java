package kr.or.iei.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.product.model.vo.ReviewProduct;
import kr.or.iei.review.model.dao.ReviewDao;
import kr.or.iei.review.model.vo.Review;

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
	public Review selectOneReview(int reviewNo, int customerNo) {
		int result = reviewDao.updateReadCount(reviewNo);
		if(result>0) {
			Review review = reviewDao.selectOneReview(reviewNo,customerNo);
			return review;
		}else {
			return null;			
		}
	}

	@Transactional
	public int insertReviewLike(int reviewNo, int customerNo) {
		int result = reviewDao.insertReviewLike(reviewNo,customerNo);
		int likeCount = reviewDao.likeCount(reviewNo);
		return likeCount;
	}


	public int removeReviewLike(int reviewNo, int customerNo) {
		int result = reviewDao.removeReviewLike(reviewNo,customerNo);
		int likeCount = reviewDao.likeCount(reviewNo);
		return likeCount;
	}

}
