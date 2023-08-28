package kr.or.iei.page.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class PageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ProductRowMapper productRowMapper;
	
	public List searchProduct(String searchWord) {
		//가져와야되는 정보: productNo,sellerNo,productName,productImg,productPrice,categoryNo
		//검색했을 시 %검색단어% 쿼리
		String query = "select * from product_tbl where product_name like ?";
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
		String query="select";
		return null;
	}
	public List searchNewList() {
		String query = "select * from product_tbl order by product_reg_date DESC";
		List newPrd = jdbc.query(query, productRowMapper);
		return newPrd;
	}
	public List searchAll() {
		String query = "select * from product_tbl";
		List allPrd = jdbc.query(query, productRowMapper);
		return allPrd;
	}
	public List searchEarring() {
		String query = "select * from product_tbl where category_no = 1";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchNecklace() {
		String query = "select * from product_tbl where category_no = 2";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchRing() {
		String query = "select * from product_tbl where category_no = 3";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchBracelet() {
		String query = "select * from product_tbl where category_no =4";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchWatch() {
		String query = "select * from product_tbl where category_no = 5";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchHair() {
		String query = "select * from product_tbl where category_no = 6";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
	public List searchOther() {
		String query = "select * from product_tbl where category_no = 7";
		List list = jdbc.query(query, productRowMapper);
		return list;
	}
}
