package kr.or.iei.admin.model.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.admin.model.vo.AdminRowMapper;
import kr.or.iei.admin.model.vo.ChartRowMapper;
import kr.or.iei.admin.model.vo.MenuChartRowMapper;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeRowMapper;
import kr.or.iei.product.model.vo.CategoryRowMapper;
import kr.or.iei.product.model.vo.ProductDetailRowMapper;
import kr.or.iei.product.model.vo.ProductOptionRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.qna.model.vo.QnaCommentRowMapper;
import kr.or.iei.qna.model.vo.QnaRowMapper;
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
	@Autowired
	private ChartRowMapper chartRowMapper;
	@Autowired
	private MenuChartRowMapper menuChartRowMapper;
	@Autowired
	private ProductOptionRowMapper productOptionRowMapper;
	@Autowired
	private QnaRowMapper qnaRowMapper;
	@Autowired
	private QnaCommentRowMapper qnaCommentRowMapper;
	
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
		String query = "SELECT s.SELLER_ID,pc.* FROM SELLER_TBL s,(SELECT p.*,c.CATEGORY_NAME FROM PRODUCT_TBL p,(SELECT * FROM PRODUCT_CATEGORY_TBL) c WHERE p.CATEGORY_NO=c.CATEGORY_NO) pc WHERE pc.SELLER_NO=s.SELLER_NO AND PRODUCT_NAME LIKE ? AND PRODUCT_CHECK = ? ORDER BY PRODUCT_NO DESC";
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
		if(list.isEmpty()) {
			return null;
		}else {
			return (Admin)list.get(0);			
		}
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

	public List salesList() {
		String query = "SELECT oldl.*,p.PRODUCT_PRICE,p.SELLER_NO FROM PRODUCT_TBL p, (SELECT ol.ORDER_LIST_DATE, old.* FROM ORDER_LIST_TBL ol, (SELECT op.PRODUCT_NO, o.order_count, o.order_state, o.ORDER_LIST_NO FROM PRODUCT_OPTION_TBL op, (SELECT * FROM ORDER_TBL) o WHERE op.PRODUCT_OPTION_NO = o.product_option_no) old WHERE ol.ORDER_LIST_NO=old.ORDER_LIST_NO) oldl WHERE p.PRODUCT_NO=oldl.PRODUCT_NO";
			List list = jdbc.query(query, chartRowMapper);
		return list;
	}

	public List salesListTest() {
		String query ="SELECT oldl.ORDER_LIST_DATE,sum(p.PRODUCT_PRICE*oldl.ORDER_COUNT) X FROM PRODUCT_TBL p, (SELECT ol.ORDER_LIST_DATE, old.* FROM ORDER_LIST_TBL ol, (SELECT op.PRODUCT_NO, o.order_count, o.order_state, o.ORDER_LIST_NO FROM PRODUCT_OPTION_TBL op, (SELECT * FROM ORDER_TBL) o WHERE op.PRODUCT_OPTION_NO = o.product_option_no) old WHERE ol.ORDER_LIST_NO=old.ORDER_LIST_NO) oldl WHERE p.PRODUCT_NO=oldl.PRODUCT_NO AND p.SELLER_NO = 21 and oldl.order_list_date between to_char(sysdate-6,'yyyy-mm-dd') and to_char(sysdate,'yyyy-mm-dd') and oldl.order_state >= 1 and oldl.order_state <= 4 GROUP BY oldl.ORDER_LIST_DATE ORDER by oldl.ORDER_LIST_DATE";
		return jdbc.query(query, menuChartRowMapper);
	}

	public List question() {
		String query = "SELECT * FROM QUESTION_TBL";
		return jdbc.query(query, qnaRowMapper);
	}

	public List selectQna(String qNo) {
		String query ="SELECT * FROM question_tbl WHERE question_no=?";
		return jdbc.query(query, qnaRowMapper,qNo);
	}

	public List selectSellerP(String sId) {
		String query = "SELECT p.* FROM seller_tbl s, (SELECT * FROM product_tbl ) p WHERE s.seller_no = p.seller_no and s.seller_id=?";
		return jdbc.query(query, productRowMapper, sId);
	}

	public List productO(int pNo) {
		String query ="SELECT * FROM product_option_tbl WHERE product_NO=?";
		return jdbc.query(query, productOptionRowMapper,pNo);
	}

	public List selectCustomerB(String cId) {
		String query = "SELECT PRODUCT_NO, SELLER_NO, PRODUCT_NAME, PRODUCT_IMG, PRODUCT_PRICE, PRODUCT_CONTENT, PRODUCT_CONTENT_DETAILS, PRODUCT_REG_DATE, PRODUCT_CHECK, CATEGORY_NO  FROM view1 WHERE customer_id LIKE ?";
		return jdbc.query(query, productRowMapper , cId);
		
	}

	public int qnaAnswer(int qnaNo, String qnaAnswerComment, String adminId) {
		String query = "INSERT into QUESTION_COMMENT_TBL values(QUESTION_COMMENT_SEQ.NEXTVAL,?,?,?,to_char(sysdate,'YYYY-MM-DD'))";
		return jdbc.update(query,qnaNo,adminId,qnaAnswerComment);
	}

	public List selectQnaC(String qNo) {
		String query ="SELECT * FROM QUESTION_COMMENT_TBL where QUESTION_no = ?";
		return jdbc.query(query, qnaCommentRowMapper,qNo);
	}

	public List question(String input) {
		String query = "SELECT * FROM QUESTION_TBL where QUESTION_TITLE like ?";
		return jdbc.query(query, qnaRowMapper,"%"+input+"%");
	}

	public int noticeDel(int noticeNo) {
		String query = "DELETE FROM notice_tbl where notice_no = ?";
		return jdbc.update(query,noticeNo);
	}

	public int qnaAnswerDel(int qnaCommentNo) {
		String query = "DELETE FROM QUESTION_COMMENT_TBL where QUESTION_COMMENT_NO = ?";
		return jdbc.update(query,qnaCommentNo);
	}

	public int modifyGo(int pw, String phone, String adminId) {
		String qurey = "UPDATE admin_TBL SET admin_pw=?,admin_phone=?  WHERE admin_id=?";
		return jdbc.update(qurey, pw,phone,adminId);
	}
}
