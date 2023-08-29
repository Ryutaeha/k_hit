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
		a.setMemberCode(rs.getInt("member_code"));
		a.setAdminId(rs.getString("admin_id"));
		a.setAdminPw(rs.getString("admin_pw"));
		a.setAdminPhone(rs.getString("admin_phone"));
		a.setAdminImg(rs.getString("admin_img"));
		return a;
	}
}
