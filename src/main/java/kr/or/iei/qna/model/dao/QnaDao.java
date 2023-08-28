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
	
	
	public List searchAllQna() {
		String query="";
		return null;
	}
}
