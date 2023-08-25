package kr.or.iei.review.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewRowMapper;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	
	public int insertReview(Review review) {
		String query = "insert into review_tbl values(review_seq.nextval,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default";
		Object[] params = {review.getOrderNo(),review.getReviewWriter(),review.getStarCount(),review.getReviewContent(),review.getFilepath()};
		int result = jdbc.update(query,params);
		return result;
	}

}
