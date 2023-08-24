package kr.or.iei.customer.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdressRowMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address a  = new Address();
		a.setAddressDetail(rs.getString("address_detail"));
		a.setAddressName(rs.getString("address_name"));
		a.setAddressNo(rs.getInt("address_no"));
		a.setAddressPhone(rs.getString("address_phone"));
		a.setAddressPostalCode(rs.getString("address_postal_code"));
		a.setAddressSimple(rs.getString("address_simple"));
		a.setCustomerNo(rs.getInt("customer_no"));
		return a;
	}
}
