package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class ReviewProductRowMapper  implements RowMapper<ReviewProduct>{

	@Override
	public ReviewProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewProduct rp = new ReviewProduct();
		rp.setOptionColor(rs.getString("option_color"));
		rp.setOptionSize(rs.getString("option_size"));
		rp.setOrderNo(rs.getInt("order_no"));
		rp.setProductImg(rs.getString("product_img"));
		rp.setProductName(rs.getString("product_name"));
		return rp;
	}
	
}
