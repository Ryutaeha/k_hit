package kr.or.iei.customer.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;

@Repository
public class CustomerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	
	public int insertCustomer(Customer customer, String customerEmail2) {
		String query = "insert into customer_tbl values(customer_seq.nextval,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {customer.getCustomerId(),customer.getCustomerPw(),customer.getCustomerName(),customer.getCustomerPhone(),customer.getCustomerEmail()+"@"+customerEmail2};
		int result = jdbc.update(query,params);
		return result;
	}

	public Customer selectCustomerId(String customerId) {
		String query = "select * from customer_tbl where customer_id=?";
		List list = jdbc.query(query, customerRowMapper, customerId);
		if(list.isEmpty()) {
			return null;
		}
		return (Customer)list.get(0);
	}

	public Customer selectOneCustomer(String customerSignId, String customerSignPw) {
		String query = "select * from customer_tbl where customer_id=? and customer_pw=?";
		List list = jdbc.query(query,customerRowMapper,customerSignId,customerSignPw);
		if(list.isEmpty()) {
			return null;			
		}
		return (Customer)list.get(0);
	}

	public Cart selectMyCart(int cartNo) {
		String query = "";
		return null;
	}


}
