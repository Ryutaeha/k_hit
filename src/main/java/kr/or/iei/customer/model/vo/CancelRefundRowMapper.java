package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CancelRefundRowMapper implements RowMapper<CancelRefund>{

	@Override
	public CancelRefund mapRow(ResultSet rs, int rowNum) throws SQLException {
		CancelRefund cr = new CancelRefund();
		cr.setCustomerNo(rs.getInt("customer_no"));
		cr.setOptionColor(rs.getString("option_color"));
		cr.setOptionSize(rs.getString("option_size"));
		cr.setOrderCount(rs.getInt("order_count"));
		cr.setOrderListDate(rs.getString("order_list_date"));
		cr.setOrderState(rs.getInt("order_state"));
		cr.setProductImg(rs.getNString("product_img"));
		cr.setProductName(rs.getString("product_name"));
		cr.setProductPrice(rs.getInt("product_price"));
		cr.setOrderNo(rs.getInt("order_no"));
		return cr;
	}

}
