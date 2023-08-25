package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product p = new Product();
		p.setCategoryNo(rs.getInt("category_no"));
		p.setProductCheck(rs.getInt("product_check"));
		p.setProductContent(rs.getString("product_content"));
		p.setProductContentDetails(rs.getString("product_content_details"));
		p.setProductImg(rs.getString("product_img"));
		p.setProductName(rs.getString("product_name"));
		p.setProductNo(rs.getInt("product_no"));
		p.setProductPrice(rs.getInt("product_price"));
		p.setProductRegDate(rs.getString("product_reg_date"));
		p.setSellerNo(rs.getInt("seller_no"));
		return p;
	}

}
