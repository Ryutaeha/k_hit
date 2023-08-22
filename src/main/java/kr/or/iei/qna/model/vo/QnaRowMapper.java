package kr.or.iei.qna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QnaRowMapper implements RowMapper<Qna>{

	@Override
	public Qna mapRow(ResultSet rs, int rowNum) throws SQLException {
		Qna q = new Qna();
		return q;
	}
	
}
