package kr.or.iei.qna.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.qna.model.vo.Qna;
import kr.or.iei.qna.model.vo.QnaRowMapper;
import kr.or.iei.seller.model.vo.Seller;
import kr.or.iei.seller.model.vo.SellerRowMapper;

@Repository
public class QnaDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private QnaRowMapper qnaRowMapper;
	@Autowired
	private SellerRowMapper sellerRowMapper;
	
	
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


	public int updateReadCount(int questionNo) {
		String query = "update notice set read_cont = read_count+1 where question_no=?";
		Object[] params = {questionNo};
		int result =jdbc.update(query,params);
		return result;
	}


	public List selectQnaFile(int questionNo) {
		String query = "select * from ";
		return null;
	}


	public Seller selectOneQna(int sellerNo) {
		String query = "select * from seller_tbl where seller_no = ?";
		List list = jdbc.query(query, sellerRowMapper,sellerNo);
		return (Seller)list.get(0);
	}

	public List selectSellerFile(int sellerNo) {
		String query = "select * from seller_file where seller_no=?";
		//List list = jdbc.query(query, )
		return null;
	}
}











