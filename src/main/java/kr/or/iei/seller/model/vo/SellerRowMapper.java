package kr.or.iei.seller.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SellerRowMapper implements RowMapper<Seller>{

	@Override
	public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seller s = new Seller();
		return s;
	}

}
