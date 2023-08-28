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
		q.setQuestionNo(rs.getInt("question_no"));
		q.setQuestionTitle(rs.getString("question_title"));
		q.setQuestionContent(rs.getString("question_content"));
		q.setQuestionCusWriter(rs.getString("question_cus_writer"));
		q.setQuestionSellWriter(rs.getString("question_sell_writer"));
		q.setQuestionDate(rs.getString("question_date"));
		q.setQuestionReadCount(rs.getInt("question_read_count"));
		return q;
	}
	
}
