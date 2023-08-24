package kr.or.iei.seller.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.ProductOptionRowMapperSecond;
import kr.or.iei.product.model.vo.ProductOptionRowMapper;
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
	@Autowired
	private ProductRowMapper productRowMapperSecond;
	@Autowired
	private ProductOptionRowMapperSecond productOptionRowMapper;
	@Autowired
	private ProductOptionRowMapperSecond productOptionRowMapperSecond;
	
	public List selectProductList(int sellerNo, int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from PRODUCT_TBL where seller_no=? order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, productRowMapper,sellerNo,start,end);
		return list;
	}

	public int selectProductTotalCount(int sellerNo) {
		String query = "select count(*) as cnt from PRODUCT_TBL where seller_no=?";
		
		int totalCount = jdbc.queryForObject(query, Integer.class,sellerNo);
		//int totalCount = jdbc.(query,params);
		return totalCount;
	}
	public int insertSeller(Seller s,String customerEmail2) {
		String query="insert into seller_tbl values(seller_seq.nextval,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {s.getSellerId(),s.getSellerPw(),s.getSellerName(),s.getSellerImg(),s.getSellerPhone(),(s.getSellerEmail()+"@"+customerEmail2),s.getSellerIntroduce()};
		int result = jdbc.update(query,params);
		return result;
	}

	public Seller selectOneSeller(String sellerSignId, String sellerSignPw) {
		String query = "select * from seller_tbl where seller_id=? and seller_pw=?";
		List list = jdbc.query(query, sellerRowMapper, sellerSignId, sellerSignPw);
		if(list.isEmpty()) {
			return null;
		}
		return (Seller)list.get(0);
	}

	public List addNewProductList(int sellerNo, int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from PRODUCT_TBL where seller_no=? order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, productRowMapper,sellerNo,start,end);
		return list;
	}

	public List productStockManagement(int sellerNo, int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from PRODUCT_OPTION_TBL JOIN PRODUCT_TBL USING (PRODUCT_NO) where seller_no=? order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, productOptionRowMapperSecond,sellerNo,start,end);
		return list;
	}

	public int selectProductOptionTotalCount(int sellerNo) {
		String query = "SELECT count(*) FROM PRODUCT_OPTION_TBL WHERE PRODUCT_NO IN (SELECT PRODUCT_NO FROM PRODUCT_TBL WHERE SELLER_NO=?)";
		int totalCount = jdbc.queryForObject(query, Integer.class,sellerNo);
		return totalCount;
	}
}
