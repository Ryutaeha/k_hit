package kr.or.iei.admin.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MenuChartRowMapper implements RowMapper<MenuChart>{
	
	@Override
	public MenuChart mapRow(ResultSet rs, int rowNum) throws SQLException{
		MenuChart mc = new MenuChart();
		mc.setOrderListDate(rs.getString("Order_List_Date"));
		mc.setX(rs.getInt("X"));
		return mc;
	}
}
