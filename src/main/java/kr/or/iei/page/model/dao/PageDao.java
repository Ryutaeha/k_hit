package kr.or.iei.page.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.page.model.vo.BestListRowMapping;
import kr.or.iei.page.model.vo.ReviewContentStarRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class PageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private ReviewContentStarRowMapper reviewContentStarRowMapper;
	@Autowired
	private BestListRowMapping bestListRowMapping;
	
	
	public List searchProduct(String searchWord) {
		//가져와야되는 정보: productNo,sellerNo,productName,productImg,productPrice,categoryNo
		//검색했을 시 %검색단어% 쿼리
		String query = "select * from product_tbl where product_name like ? and PRODUCT_CHECK=2";
		List prodcutList = jdbc.query(query, productRowMapper,"%"+searchWord+"%");
		return prodcutList;
	}
	/*
	public String searchProductCount(String searchWord) {
		//검색된 리스트 갯수
		String query = "select count(*) from(select * from product_tbl where product_name like ?)search";
		String searchCount = jdbc.queryForObject(query,String.class,"'%"+searchWord+"%'");
		return searchCount;
	}
	*/
	//검색된 product_name으로 달린리뷰내용 가져오기
	public List reveiwContent(String searchWord) {
		String query="select review_content,star_count from review_tbl where order_no in (select product_option_no from product_option_tbl where product_no in(select product_no from product_tbl where product_name like ? and PRODUCT_CHECK=2))order by review_date desc";
		List reviewContent = jdbc.query(query,reviewContentStarRowMapper,"%"+searchWord+"%");
		return reviewContent;
	}
	public List searchNewList() {
		String query = "select * from product_tbl where PRODUCT_CHECK=2 order by product_reg_date DESC";
		List newPrd = jdbc.query(query, productRowMapper);
		return newPrd;
	}
	public List searchBest() {
		String query = "select * from (select rownum as bestList ,best.* from (SELECT p.PRODUCT_NO, p.PRODUCT_NAME,p.PRODUCT_CONTENT,p.PRODUCT_CONTENT_DETAILS,p.PRODUCT_IMG,p.PRODUCT_PRICE, sum(opo.ORDER_COUNT) FROM PRODUCT_TBL p,(SELECT po.PRODUCT_NO, o.ORDER_COUNT FROM ORDER_TBL o,(SELECT product_option_no,PRODUCT_NO FROM PRODUCT_OPTION_TBL) po WHERE po.product_option_no =o.PRODUCT_OPTION_NO) opo where opo.PRODUCT_NO = p.PRODUCT_NO GROUP BY p.PRODUCT_NO, p.PRODUCT_NAME,p.PRODUCT_CONTENT,p.PRODUCT_CONTENT_DETAILS,p.PRODUCT_IMG,p.PRODUCT_PRICE)best)";
		List bestPrd = jdbc.query(query,bestListRowMapping);
		return bestPrd;
	}
	public List searchAll() {
		String query = "select * from product_tbl where PRODUCT_CHECK=2";
		List allPrd = jdbc.query(query, productRowMapper);
		return allPrd;
	}
	public List searchEarring() {
		String query = "select * from product_tbl where category_no = 1 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchNecklace() {
		String query = "select * from product_tbl where category_no = 2 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchRing() {
		String query = "select * from product_tbl where category_no = 3 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchBracelet() {
		String query = "select * from product_tbl where category_no =4 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchWatch() {
		String query = "select * from product_tbl where category_no = 5 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchHair() {
		String query = "select * from product_tbl where category_no = 6 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchOther() {
		String query = "select * from product_tbl where category_no = 7 and PRODUCT_CHECK=2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	
	public List searchNewListFive() {
		String query = "select * from (select rownum as rnum ,p.* from (select * from product_tbl where PRODUCT_CHECK=2  order by  product_reg_date DESC)p) where rnum<6";
		List newPrd = jdbc.query(query, productRowMapper);
		return newPrd;
		
	}
	public List searchBestListFive() {
		String query = "select * from (select rownum as bestlist ,best.* from (SELECT p.PRODUCT_NO, p.PRODUCT_NAME,p.PRODUCT_CONTENT,p.PRODUCT_CONTENT_DETAILS,p.PRODUCT_IMG,p.PRODUCT_PRICE, sum(opo.ORDER_COUNT) FROM PRODUCT_TBL p,(SELECT po.PRODUCT_NO, o.ORDER_COUNT FROM ORDER_TBL o,(SELECT product_option_no,PRODUCT_NO FROM PRODUCT_OPTION_TBL) po WHERE po.product_option_no =o.PRODUCT_OPTION_NO) opo where opo.PRODUCT_NO = p.PRODUCT_NO GROUP BY p.PRODUCT_NO, p.PRODUCT_NAME,p.PRODUCT_CONTENT,p.PRODUCT_CONTENT_DETAILS,p.PRODUCT_IMG,p.PRODUCT_PRICE)best) where bestlist<6";
		List list = jdbc.query(query,bestListRowMapping );
		return list;
	}
}
