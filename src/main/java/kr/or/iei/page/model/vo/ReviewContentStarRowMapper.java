package kr.or.iei.page.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewContentStarRowMapper implements RowMapper<ReviewContentStar>{

	@Override
	public ReviewContentStar mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewContentStar rcs = new ReviewContentStar();
		rcs.setReviewContent(rs.getString("review_content"));
		rcs.setStarCount(rs.getInt("star_count"));
		return rcs;
	}

}
