package kr.or.iei.admin.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException{
		Admin a = new Admin();
		return a;
	}
}
