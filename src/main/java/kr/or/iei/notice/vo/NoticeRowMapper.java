package kr.or.iei.notice.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NoticeRowMapper implements RowMapper<Notice>{

	@Override
	public Notice mapRow(ResultSet rs, int rowNum) throws SQLException{
		Notice n = new Notice();
		n.setNoticeNo(rs.getInt("notice_no"));
		n.setNoticeTitle(rs.getString("notice_title"));
		n.setNoticeContent(rs.getString("notice_content"));
		n.setNoticeDate(rs.getString("notice_date"));
		n.setNoticeWriter(rs.getString("notice_writer"));
		n.setNoticeFix(rs.getInt("notice_fix"));
		n.setNoticeReadCount(rs.getInt("notice_read_count"));
		return n;
	}
}
