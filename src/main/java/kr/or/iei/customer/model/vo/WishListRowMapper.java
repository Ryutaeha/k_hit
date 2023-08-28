package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Component;

@Component
public class WishListRowMapper implements RowMapper<WishList> {

	@Override
	public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishList wl = new WishList();
		wl.setCustomerNo(rs.getInt("customer_no"));
		wl.setProductNo(rs.getInt("product_no"));
		return wl;
	}
	
}
