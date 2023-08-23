package kr.or.iei.seller.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.seller.model.vo.Seller;
import kr.or.iei.seller.model.vo.SellerRowMapper;

@Repository
public class SellerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private SellerRowMapper sellerRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	
	public List selectProductList(int sellerNo, int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from PRODUCT_TBL where seller_no=21 order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, productRowMapper,start,end);
		return list;
	}

	public int selectProductTotalCount(int sellerNo) {
		String query = "select count(*) as cnt from PRODUCT_TBL where seller_no=?";
		
		int totalCount = jdbc.queryForObject(query, Integer.class,21);
		//int totalCount = jdbc.(query,params);
		return totalCount;
	}

}
