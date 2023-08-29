package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductOptionRowMapperSecond implements RowMapper<ProductOptionSecond>{

	@Override
	public ProductOptionSecond mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductOptionSecond po = new ProductOptionSecond();
		po.setOptionColor(rs.getString("option_color"));
		po.setOptionSize(rs.getString("option_size"));
		po.setOptionStock(rs.getInt("option_stock"));
		po.setProductNo(rs.getInt("product_no"));
		po.setProductOptionNo(rs.getInt("product_option_no"));
		po.setOutOfStock(rs.getInt("out_of_stock"));
		//판매상품 재고관리를 위해 추가한 부분
		po.setProductName(rs.getString("product_name"));
		return po;
	}

}
