package kr.or.iei.seller.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CancelListRowMapper implements RowMapper<CancelList>{

	@Override
	public CancelList mapRow(ResultSet rs, int rowNum) throws SQLException {
		CancelList cl = new CancelList();
		cl.setOrderNo(rs.getInt("order_no"));
		cl.setOrderListNo(rs.getInt("order_list_no"));
		cl.setAddressNo(rs.getInt("address_no"));
		cl.setProductOptionNo(rs.getInt("product_option_no"));
		cl.setOrderCount(rs.getInt("order_count"));
		cl.setOrderState(rs.getInt("order_state"));
		cl.setOrderRequest(rs.getString("order_request"));
		return cl;
	}
	
}
