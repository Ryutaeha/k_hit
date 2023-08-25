package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AddNewProductRowMapper implements RowMapper<AddNewProduct>{

	@Override
	public AddNewProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddNewProduct anp = new AddNewProduct();
		anp.setCategoryNo(rs.getInt("category_no"));
		//anp.setOptionColor(rs.getString("option_color"));
		//anp.setOptionSize(rs.getString("option_size"));
		anp.setOptionStock(rs.getInt("option_stock"));
		anp.setProductContent(rs.getString("product_content"));
		anp.setProductContentDetails(rs.getString("product_content_details"));
		anp.setProductImg(rs.getString("product_img"));
		anp.setProductName(rs.getString("product_name"));
		anp.setProductNo(rs.getInt("product_no"));
		anp.setProductOptionNo(rs.getInt("product_option_no"));
		anp.setProductPrice(rs.getInt("product_price"));
		anp.setProductRegDate(rs.getString("product_reg_date"));
		anp.setSellerNo(rs.getInt("seller_no"));
		return null;
	}
	
}
