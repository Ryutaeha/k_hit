package kr.or.iei.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeRowMapper;
@Repository
public class NoticeDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	
	public List searchAllNotice(int start, int end) {
		String query="select * from(select rownum as rnum,n.* from(SELECT * FROM notice_TBL order by 1 desc)n)where rnum between ? and ?";
		return jdbc.query(query, noticeRowMapper,start,end);
	}

	public int searchNoticeTotalCount() {
		String query = "select count(*) from notice_tbl";
		return jdbc.queryForObject(query, Integer.class);
	}

	public int updateReadCount(int noticeNo) {
		String query ="update notice_tbl set notice_read_count = notice_read_count+1 where notice_no=?";
		return jdbc.update(query,noticeNo);
	}

	public Notice selectOneNotice(int noticeNo) {
		String query = "select * from notice_tbl where notice_no = ?";
		List list = jdbc.query(query, noticeRowMapper, noticeNo);
		return (Notice)list.get(0);
	}

}
