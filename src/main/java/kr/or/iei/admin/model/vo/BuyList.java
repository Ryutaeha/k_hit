package kr.or.iei.admin.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.or.iei.product.model.vo.Product;

@Component
public class BuyList implements RowMapper<List> {
	
	@Override
	public List mapRow(ResultSet rs, int rowNum) throws SQLException{
		List list = null;
		String customerId = rs.getString("customer_Id");
		String orderList = rs.getString("order_list_data");
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
		list.add(customerId);
		list.add(orderList);
		list.add(p);
		return list;
	}
}
