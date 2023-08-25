package kr.or.iei.review.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewRowMapper  implements RowMapper<Review>{

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review r = new Review();
		r.setFilepath(rs.getString("filepath"));
		r.setOrderNo(rs.getInt("order_no"));
		r.setReadCount(rs.getInt("read_count"));
		r.setReviewContent(rs.getString("review_content"));
		r.setReviewDate(rs.getString("review_date"));
		r.setReviewNo(rs.getInt("review_no"));
		r.setStarCount(rs.getInt("star_count"));
		r.setReviewWriter(rs.getNString("review_writer"));
		return r;
	}

}
