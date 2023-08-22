package kr.or.iei.customer.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;

@Repository
public class CustomerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	
	public int insertCustomer(Customer customer) {
		String query = "insert into customer_tbl values(customer_seq.nextval,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {customer.getCustomerId(),customer.getCustomerPw(),customer.getCustomerName(),customer.getCustomerPhone(),customer.getCustomerEmail()};
		int result = jdbc.update(query,params);
		return result;
	}

}
