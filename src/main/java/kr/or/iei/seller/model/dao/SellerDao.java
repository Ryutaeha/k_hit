package kr.or.iei.seller.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.product.model.vo.ProductOptionRowMapperSecond;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductOptionRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.seller.model.vo.CancelList;
import kr.or.iei.seller.model.vo.CancelListRowMapper;
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
	@Autowired
	private CancelListRowMapper cancelListRowMapper;
	
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

	public int changeOptionStock(int optionStock, int productOptionNo) {
		String query = "UPDATE PRODUCT_OPTION_TBL SET option_stock=? WHERE product_option_no=?";
		Object[] params = {optionStock, productOptionNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateSeller(String customerEmail2, Seller s) {
		//비밀번호,이름,이미지,전화번호,이메일,자기소개
		String query = "update seller_tbl set seller_pw=?,seller_name=?,seller_img=?,seller_phone=?,seller_email=?,seller_introduce=? where seller_id=?";
		Object[] params = {s.getSellerPw(),s.getSellerName(),s.getSellerImg(),s.getSellerPhone(),(s.getSellerEmail()+"@"+customerEmail2),s.getSellerIntroduce(),s.getSellerId()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int addNewProduct(Product p, int sellerNo) {
		String query = "INSERT INTO PRODUCT_TBL VALUES(PRODUCT_SEQ.NEXTVAL,?,?,?,?,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD'),DEFAULT,?)";
		Object[] params = {sellerNo,p.getProductName(),p.getProductImg(),p.getProductPrice(),p.getProductContent(),p.getProductContentDetails(),p.getCategoryNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int getProductNo() {
		String query = "select max(product_no) from product_tbl";
		int productNo = jdbc.queryForObject(query, Integer.class);
		return productNo;
	}

	public int addNewProductOption(ProductOption productOption) {
		String query = "insert into PRODUCT_OPTION_TBL values(PRODUCT_OPTION_SEQ.nextval,?,?,?,default)";
		Object[] params = {productOption.getProductNo(),productOption.getOptionSize(),productOption.getOptionColor()};
		int result = jdbc.update(query,params);
		return result;
	}
	//판매자 번호?의 취소요청를 한 리스트
	public List cancelList(Seller s) {
		String query="select * from order_tbl where product_option_no in(select product_option_no from product_option_tbl where product_no in (select product_no from product_tbl where seller_no=?))and order_state = 4";
		List list = jdbc.query(query,cancelListRowMapper,s.getSellerNo());
		return list;
	}

	public List refundList(Seller s) {
		String query="select * from order_tbl where product_option_no in(select product_option_no from product_option_tbl where product_no in (select product_no from product_tbl where seller_no=?))and order_state = 5";
		List list = jdbc.query(query,cancelListRowMapper,s.getSellerNo());
		return list;
	}
}
