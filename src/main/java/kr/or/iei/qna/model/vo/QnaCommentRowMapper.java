package kr.or.iei.qna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QnaCommentRowMapper implements RowMapper<QnaComment>{
	@Override
	public QnaComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		QnaComment qc = new QnaComment();
		qc.setQuestionCommentNo(rs.getInt("QUESTION_COMMENT_NO"));
		qc.setQuestionNo(rs.getInt("QUESTION_NO"));
		qc.setQuestionWriter(rs.getString("QUESTION_WRITER"));
		qc.setQuestionContent(rs.getString("QUESTION_CONTENT"));
		qc.setQuestionDate(rs.getString("QUESTION_DATE"));
		return qc;
	}

}
