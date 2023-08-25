package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductOptionRowMapper implements RowMapper<ProductOptionSecond>{

	@Override
	public ProductOptionSecond mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductOptionSecond po = new ProductOptionSecond();
		po.setOptionColor(rs.getString("option_color"));
		po.setOptionSize(rs.getString("option_size"));
		po.setOptionStock(rs.getInt("option_stock"));
		po.setProductNo(rs.getInt("product_no"));
		po.setProductOptionNo(rs.getInt("product_option_no"));
		return po;
	}

}
