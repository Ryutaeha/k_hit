package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setAddressNo(rs.getInt("address_no"));
		o.setOrderCount(rs.getInt("order_count"));
		o.setOrderListNo(rs.getInt("order_list_no"));
		o.setOrderNo(rs.getInt("order_no"));
		o.setOrderRequest(rs.getString("order_request"));
		o.setOrderState(rs.getInt("order_state"));
		o.setProductOptionNo(rs.getInt("product_option_no"));
		// TODO Auto-generated method stub
		return o;
	}
	
}
