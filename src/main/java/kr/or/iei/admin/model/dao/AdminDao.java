package kr.or.iei.admin.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.admin.model.vo.AdminRowMapper;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeRowMapper;
import kr.or.iei.product.model.vo.CategoryRowMapper;
import kr.or.iei.product.model.vo.ProductDetailRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.seller.model.vo.Seller;
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
	@Autowired
	private ProductDetailRowMapper productDetailRowMapper;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	
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
		List list = jdbc.query(query, productDetailRowMapper,"%"+input+"%",categoryNo,productCheck);
		return list;
	}
	public List product(String input, int productCheck) {
		List list;
		String query = "SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO AND PRODUCT_NAME LIKE ? AND PRODUCT_CHECK = ?";
		list = jdbc.query(query, productDetailRowMapper,"%"+input+"%",productCheck);
		return list;
	}
	public List product(int pNo) {
		List list;
		String query = "SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO and pc.product_no = ?";
		list = jdbc.query(query, productDetailRowMapper,pNo);
		return list;
	}

	public Seller selectSeller(String sId) {
		String query = "SELECT * FROM SELLER_TBL WHERE SELLER_ID = ?";
		List list = jdbc.query(query, sellerRowMapper, sId);
		return (Seller)list.get(0);
	}

	public Customer selectCustomer(String cId) {
		String query = "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID = ?";
		List list = jdbc.query(query, customerRowMapper, cId);
		return (Customer)list.get(0);
	}

	public Admin adminLogin(String adminSignId, String adminSignPw) {
		String query = "SELECT * FROM ADMIN_TBL WHERE ADMIN_ID=? and ADMIN_PW=?";
		List list = jdbc.query(query, adminRowMapper, adminSignId, adminSignPw);
		return (Admin)list.get(0);
	}

	public int insertNotice(Notice n) {
		String query = "INSERT INTO NOTICE_TBL VALUES(NOTICE_SEQ.NEXTVAL,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD'),?,0,0)";
		return jdbc.update(query, n.getNoticeTitle(),n.getNoticeContent(),n.getNoticeWriter());		
	}

	public List<Notice> noticeList(int noticeFix, String input) {
		String query  ="SELECT * FROM NOTICE_TBL WHERE NOTICE_FIX = ? and notice_title Like ?";
		List list = jdbc.query(query, noticeRowMapper, noticeFix,"%"+input+"%");
		return list;
	}

	public List<Notice> noticeList(String input) {
		String query  ="SELECT * FROM NOTICE_TBL where notice_title Like ?";
		List list = jdbc.query(query, noticeRowMapper,"%"+input+"%");
		return list;
	}

	public Notice noticeView(int nNo) {
		String query = "SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		List list = jdbc.query(query, noticeRowMapper, nNo);
		return (Notice)list.get(0);
	}

	public int productCheckChange(int productCheck, int productNo) {
		String qurey = "UPDATE product_tbl SET product_check = ?  WHERE product_no = ?";
		return jdbc.update(qurey,productCheck,productNo);
	}

	public int fix(int fix, int nNo) {
		String qurey = "UPDATE notice_tbl SET notice_fix = ?  WHERE notice_no=?";
		return jdbc.update(qurey, fix,nNo);
	}
}
