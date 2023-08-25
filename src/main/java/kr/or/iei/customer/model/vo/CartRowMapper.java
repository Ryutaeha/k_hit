package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CartRowMapper implements RowMapper<Cart>{

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart c = new Cart();
		c.setCartCount(rs.getInt("cart_count"));
		c.setCustomerNo(rs.getInt("customer_no"));
		c.setProductOptionNo(rs.getInt("product_option_no"));
		c.setCartNo(rs.getInt("cart_no"));
		return c;
	}

}
