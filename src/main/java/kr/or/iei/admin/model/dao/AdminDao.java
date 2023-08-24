package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.vo.AdminRowMapper;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.product.model.vo.CategoryRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.seller.model.vo.SellerRowMapper;

@Repository
public class AdminDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private AdminRowMapper adminRowMapper;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	@Autowired
	private SellerRowMapper sellerRowMapper;
	@Autowired
	private CategoryRowMapper categoryRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	public List customerList(String input) {
		System.out.println(input);
		String query = "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID LIKE ?";
		List list = jdbc.query(query, customerRowMapper, "%"+input+"%");
		return list;
	}

	public List sellerList(String input) {
		String query = "SELECT * FROM seller_tbl WHERE seller_ID LIKE ?";
		List list = jdbc.query(query, sellerRowMapper,"%"+input+"%");
		return list;
	}

	public List category() {
		String query = "SELECT * FROM PRODUCT_CATEGORY_TBL";
		List list = jdbc.query(query, categoryRowMapper);
		return list;
	}

	public List product(String input, int categoryNo, int productCheck) {
		String query = "SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO AND PRODUCT_NAME LIKE ? AND CATEGORY_NO = ? AND PRODUCT_CHECK = ?";
		List list = jdbc.query(query, productRowMapper,"%"+input+"%",categoryNo,productCheck);
		return list;
	}
	public List product(String input, int productCheck) {
		List list;
		String query = "SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO AND PRODUCT_NAME LIKE ? AND PRODUCT_CHECK = ?";
		list = jdbc.query(query, productRowMapper,"%"+input+"%",productCheck);
		return list;
	}
}
