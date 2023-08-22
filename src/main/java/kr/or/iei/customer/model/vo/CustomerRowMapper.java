package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer c = new Customer();
		c.setCustomerEmail(rs.getString("customer_email"));
		c.setCustomerEnrolldate(rs.getString("customer_enrolldate"));
		c.setCustomerId(rs.getString("customer_id"));
		c.setCustomerName(rs.getString("customer_name"));
		c.setCustomerNo(rs.getInt("customer_no"));
		c.setCustomerPhone(rs.getString("customer_phone"));
		c.setCustomerPw(rs.getString("customer_pw"));
		c.setMemberCode(rs.getInt("member_code"));
		return c;
	}

}
