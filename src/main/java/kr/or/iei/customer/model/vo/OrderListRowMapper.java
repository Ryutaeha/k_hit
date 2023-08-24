package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderListRowMapper implements RowMapper<OrderList> {

	@Override
	public OrderList mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderList ol = new OrderList(); 
		ol.setOrderListCost(rs.getInt("order_list_cost"));
		ol.setOrderListDate(rs.getNString("order_list_date"));
		ol.setOrderListNo(rs.getInt("order_list_no"));
		return ol;
	}

}
