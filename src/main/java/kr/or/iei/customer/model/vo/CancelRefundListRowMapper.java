package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CancelRefundListRowMapper  implements RowMapper<CancelRefundList>{

	@Override
	public CancelRefundList mapRow(ResultSet rs, int rowNum) throws SQLException {
		CancelRefundList crl = new CancelRefundList();
		crl.setCustomerNo(rs.getInt("customer_no"));
		crl.setOptionColor(rs.getString("option_color"));
		crl.setOptionSize(rs.getString("option_size"));
		crl.setOrderCount(rs.getInt("order_count"));
		crl.setOrderListDate(rs.getString("order_list_date"));
		crl.setOrderState(rs.getInt("order_state"));
		crl.setProductImg(rs.getNString("product_img"));
		crl.setProductName(rs.getString("product_name"));
		crl.setProductPrice(rs.getInt("product_price"));
		crl.setOrderNo(rs.getInt("order_no"));
		return crl;
	}
	
}
