package kr.or.iei.review.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewListRowMapper implements RowMapper<ReviewListProduct>{

	@Override
	public ReviewListProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewListProduct rlp = new ReviewListProduct();
		rlp.setOrderNo(rs.getInt("order_no"));
		rlp.setProductImg(rs.getString("product_img"));
		rlp.setProductName(rs.getString("product_name"));
		rlp.setFilepath(rs.getString("filepath"));
		rlp.setReviewContent(rs.getString("review_content"));
		rlp.setReviewNo(rs.getInt("review_no"));
		rlp.setReviewWriter(rs.getString("review_writer"));
		rlp.setStarCount(rs.getInt("star_count"));
		rlp.setProductNo(rs.getInt("product_no"));
		return rlp;
	}
	
}
