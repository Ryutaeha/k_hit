package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BasketRowMapper implements RowMapper<Basket>{

	@Override
	public Basket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Basket b = new Basket();
		b.setBasketCount(rs.getInt("basket_count"));
		b.setCustomerNo(rs.getInt("customer_no"));
		b.setProductOptionNo(rs.getInt("product_option_no"));
		b.setBasketNo(rs.getInt("basket_no"));
		return b;
	}

}
