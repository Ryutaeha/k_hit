package kr.or.iei.review.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.review.model.dao.ReviewDao;
import kr.or.iei.review.model.vo.Review;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	
	//리뷰 등록
	public int insertReview(Review review) {
		int result = reviewDao.insertReview(review);
		return result;
	}

}
