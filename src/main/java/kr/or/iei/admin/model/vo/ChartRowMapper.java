package kr.or.iei.admin.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ChartRowMapper implements RowMapper<Chart> {

	@Override
	public Chart mapRow(ResultSet rs, int rowNum) throws SQLException{
		Chart c = new Chart();
		c.setOrderListDate(rs.getString("Order_List_Date"));
		c.setProductNo(rs.getInt("Product_No"));
		c.setOrderCount(rs.getInt("Order_Count"));
		c.setOrderState(rs.getInt("Order_State"));
		c.setOrderListNo(rs.getInt("Order_List_No"));
		c.setProductPrice(rs.getInt("Product_Price"));
		c.setSellerNo(rs.getInt("Seller_No"));
		return c;
	}
	
}
