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
	
	public List customerList(int memberCode) {
		String query = "SELECT * FROM CUSTOMER_TBL";
		List list = jdbc.query(query, customerRowMapper);
		return list;
	}

	public List sellerList(int memberCode) {
		String query = "SELECT * FROM seller_tbl";
		List list = jdbc.query(query, sellerRowMapper);
		return list;
	}
}
