package kr.or.iei.review.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DetailReviewListRowMapper implements RowMapper<Review>{

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		review.setReviewWriter(rs.getString("review_writer"));
		review.setReviewDate(rs.getString("review_date"));
		review.setStarCount(rs.getInt("star_count"));
		review.setReviewContent(rs.getString("review_content"));
		review.setReadCount(rs.getInt("read_count"));
		return review;
	}
	

}
