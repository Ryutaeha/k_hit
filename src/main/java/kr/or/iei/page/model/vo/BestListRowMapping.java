package kr.or.iei.page.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BestListRowMapping implements RowMapper<BestList>{

	@Override
	public BestList mapRow(ResultSet rs, int rowNum) throws SQLException {
		BestList bl = new BestList();
		bl.setBestList(rs.getInt("bestlist"));
		bl.setProductNo(rs.getInt("product_no"));
		bl.setProductName(rs.getString("product_name"));
		bl.setProductContent(rs.getString("product_content"));
		bl.setProductContentDetails(rs.getString("product_content_details"));
		bl.setProductImg(rs.getString("product_img"));
		bl.setProductPrice(rs.getInt("product_price"));
		bl.setOrderCount(rs.getInt("sum(opo.order_count)"));
		return bl;
		
	}
	

}
