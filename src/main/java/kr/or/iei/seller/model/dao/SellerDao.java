package kr.or.iei.seller.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.seller.model.vo.Seller;
import kr.or.iei.seller.model.vo.SellerRowMapper;

@Repository
public class SellerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private SellerRowMapper sellerRowMapper;
	public int insertSeller(Seller s,String customerEmail2) {
		String query="insert into seller_tbl values(seller_seq.nextval,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {s.getSellerId(),s.getSellerPw(),s.getSellerName(),s.getSellerImg(),s.getSellerPhone(),(s.getSellerEmail()+"@"+customerEmail2),s.getSellerIntroduce()};
		int result = jdbc.update(query,params);
		return result;
	}
}
