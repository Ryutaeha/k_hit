package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CancelRefundRowMapper  implements RowMapper<CancelRefund>{

	@Override
	public CancelRefund mapRow(ResultSet rs, int rowNum) throws SQLException {
		CancelRefund cl = new CancelRefund();
		cl.setCustomerNo(rs.getInt("customer_no"));
		cl.setOptionColor(rs.getString("option_color"));
		cl.setOptionSize(rs.getString("option_size"));
		cl.setOrderCount(rs.getInt("order_count"));
		cl.setOrderListDate(rs.getString("order_list_date"));
		cl.setOrderState(rs.getInt("order_state"));
		cl.setProductImg(rs.getNString("product_img"));
		cl.setProductName(rs.getString("product_name"));
		cl.setProductPrice(rs.getInt("product_price"));
		// TODO Auto-generated method stub
		return cl;
	}
	
}
