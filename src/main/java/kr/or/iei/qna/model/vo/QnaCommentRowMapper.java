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
		qc.setQnaCommentNo(rs.getInt("qna_comment_no"));
		qc.setQnaCommentWriter(rs.getString("qna_comment_writer"));
		qc.setQnaCommentContent(rs.getString("qna_comment_content"));
		qc.setQnaCommentDate(rs.getString("qna_comment_date"));
		qc.setQnaRef(rs.getInt("qna_ref"));
		qc.setQnaCommentRef(rs.getInt("qna_comment_ref"));
		return qc;
	}
	

}
