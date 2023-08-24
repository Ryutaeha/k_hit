package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailRowMapper implements RowMapper<ProductDetail>{
//SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO
	@Override
	public ProductDetail mapRow(ResultSet rs, int rowNum)throws SQLException{
		ProductDetail pd = new ProductDetail();
		pd.setSellerId(rs.getString("seller_id"));
		pd.setProductNo(rs.getInt("product_no"));
		pd.setSellerNo(rs.getInt("seller_no"));
		pd.setProductName(rs.getString("product_name"));
		pd.setProductImg(rs.getString("product_img"));
		pd.setProductPrice(rs.getInt("product_price"));
		pd.setProductContent(rs.getString("product_content"));
		pd.setProductContentDetails(rs.getString("product_content_details"));
		pd.setProductRegDate(rs.getString("product_reg_date"));
		pd.setProductCheck(rs.getInt("product_check"));
		pd.setCategoryNo(rs.getInt("category_no"));
		pd.setCategoryName(rs.getString("category_name"));
		return pd;
	}
}
