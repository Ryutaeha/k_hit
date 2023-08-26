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
}
