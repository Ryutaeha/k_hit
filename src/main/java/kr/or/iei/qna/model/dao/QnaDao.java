package kr.or.iei.qna.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.qna.model.vo.QnaRowMapper;

@Repository
public class QnaDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private QnaRowMapper qnaRowMapper;
	
	
	public List searchAllQna(int start,int end) {
		String query="select * from(select rownum as rnum,n.* from(SELECT * FROM QUESTION_TBL order by 1 desc)n)where rnum between ? and ?";
		List qnaList = jdbc.query(query, qnaRowMapper,start,end);
		return qnaList;
	}


	public int searchQnaTotalCount() {
		String query = "select count(*) from question_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
}
