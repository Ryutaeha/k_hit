package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.vo.AdminRowMapper;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.seller.model.vo.SellerRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private AdminRowMapper adminRowMapper;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	@Autowired
	private SellerRowMapper sellerRowMapper;
	
	public List customerList(String input) {
		System.out.println(input);
		String query = "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID LIKE ?";
		List list = jdbc.query(query, customerRowMapper, "%"+input+"%");
		return list;
	}

	public List sellerList(String input) {
		String query = "SELECT * FROM seller_tbl WHERE seller_ID LIKE ?";
		List list = jdbc.query(query, sellerRowMapper,"%"+input+"%");
		return list;
	}

	public List category() {
		// TODO Auto-generated method stub
		return null;
	}
}
