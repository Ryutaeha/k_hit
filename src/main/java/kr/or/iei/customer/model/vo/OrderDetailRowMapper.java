package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailRowMapper  implements RowMapper<OrderDetail>{

	@Override
	public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderDetail od = new OrderDetail();
		od.setCustomerNo(rs.getInt("customer_no"));
		od.setOptionColor(rs.getString("option_color"));
		od.setOptionSize(rs.getString("option_size"));
		od.setOrderCount(rs.getInt("order_count"));
		od.setOrderListDate(rs.getString("order_list_date"));
		od.setOrderState(rs.getInt("order_state"));
		od.setProductImg(rs.getNString("product_img"));
		od.setProductName(rs.getString("product_name"));
		od.setProductPrice(rs.getInt("product_price"));
		od.setOrderNo(rs.getInt("order_no"));
		return od;
	}
	
}
