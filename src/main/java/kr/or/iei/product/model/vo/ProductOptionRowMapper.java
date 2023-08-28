package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductOptionRowMapper implements RowMapper<ProductOption>{

	@Override
	public ProductOption mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductOption po = new ProductOption();
		po.setOptionColor(rs.getString("option_color"));
		po.setOptionSize(rs.getString("option_size"));
		po.setOptionStock(rs.getInt("option_stock"));
		po.setProductNo(rs.getInt("product_no"));
		po.setProductOptionNo(rs.getInt("product_option_no"));
		return po;
	}

}
