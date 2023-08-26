package kr.or.iei.review.model.vo;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewCommentRowMapper implements RowMapper<ReviewComment>{

	@Override
	public ReviewComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewComment rc = new ReviewComment();
		rc.setReviewCommentContent(rs.getString("review_comment_content"));
		rc.setReviewCommentDate(rs.getString("review_comment_date"));
		rc.setReviewCommentNo(rs.getInt("review_comment_no"));
		rc.setReviewCommentRef(rs.getInt("review_comment_ref"));
		rc.setReviewCommentWriter(rs.getString("review_comment_writer"));
		rc.setReviewRef(rs.getInt("review_ref"));
		return rc;
	}

}
