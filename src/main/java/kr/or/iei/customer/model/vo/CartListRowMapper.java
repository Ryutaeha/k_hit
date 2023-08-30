package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CartListRowMapper implements RowMapper<CartList> {

	@Override
	public CartList mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartList cl = new CartList();
		cl.setCartCount(rs.getInt("cart_count"));
		cl.setCartNo(rs.getInt("cart_no"));
		cl.setOptionColor(rs.getString("option_color"));
		cl.setOptionSize(rs.getString("option_size"));
		cl.setProduct_name(rs.getString("product_name"));
		cl.setProductImg(rs.getNString("product_img"));
		cl.setProductPrice(rs.getInt("product_price"));
		return cl;
	}
	
}
