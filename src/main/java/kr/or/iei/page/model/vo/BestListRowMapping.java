package kr.or.iei.page.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BestListRowMapping implements RowMapper<BestList>{

	@Override
	public BestList mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return null;
	}
	

}
