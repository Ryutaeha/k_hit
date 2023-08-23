package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.vo.AdminRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private AdminRowMapper adminRowMapper;
	public List memberList(int memberCode) {
		String query = "SELECT * FROM CUSTOMER_TBL";
//		List list = jdbc.query(query, rowMapper)
		return null;
	}
}
