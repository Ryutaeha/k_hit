package kr.or.iei.seller.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SellingDetailRowMapper implements RowMapper<SellingDetail> {

	@Override
	public SellingDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		SellingDetail sd = new SellingDetail();
		sd.setSellerNo(rs.getInt("seller_no"));
		sd.setOptionColor(rs.getString("option_color"));
		sd.setOptionSize(rs.getString("option_size"));
		sd.setOrderCount(rs.getInt("order_count"));
		sd.setOrderListDate(rs.getString("order_list_date"));
		sd.setOrderState(rs.getInt("order_state"));
		sd.setProductImg(rs.getNString("product_img"));
		sd.setProductName(rs.getString("product_name"));
		sd.setProductPrice(rs.getInt("product_price"));
		return sd;
	}
	

}
