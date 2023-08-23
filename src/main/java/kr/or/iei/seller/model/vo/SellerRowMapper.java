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
		s.setMemberCode(rs.getInt("member_code"));
		s.setSellerNo(rs.getInt("seller_no"));
		s.setSellerEmail(rs.getString("seller_email"));
		s.setSellerId(rs.getString("seller_id"));
		s.setSellerPw(rs.getString("seller_pw"));
		s.setSellerName(rs.getString("seller_name"));
		s.setSellerImg(rs.getString("seller_img"));
		s.setSellerPhone(rs.getString("seller_phone"));
		s.setSellerIntroduce(rs.getString("seller_introduce"));
		s.setSellerEnrollDate(rs.getString("seller_enrolldate"));
		return s;
	}
}