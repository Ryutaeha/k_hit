package kr.or.iei.customer.model.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.customer.model.vo.WishListRowMapper;
import kr.or.iei.review.model.vo.ReviewListRowMapper;

@Repository
public class CustomerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	@Autowired WishListRowMapper wishListRowMapper;
	@Autowired
	ReviewListRowMapper reviewListRowMapper;
	
	public int insertCustomer(Customer customer, String customerEmail2) {
		String query = "insert into customer_tbl values(customer_seq.nextval,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {customer.getCustomerId(),customer.getCustomerPw(),customer.getCustomerName(),customer.getCustomerPhone(),customer.getCustomerEmail()+"@"+customerEmail2};
		int result = jdbc.update(query,params);
		return result;
	}

	public Customer selectCustomerId(String customerId) {
		String query = "select * from customer_tbl where customer_id=?";
		List list = jdbc.query(query, customerRowMapper, customerId);
		if(list.isEmpty()) {
			return null;
		}
		return (Customer)list.get(0);
	}

	public Customer selectOneCustomer(String customerSignId, String customerSignPw) {
		String query = "select * from customer_tbl where customer_id=? and customer_pw=?";
		List list = jdbc.query(query,customerRowMapper,customerSignId,customerSignPw);
		if(list.isEmpty()) {
			return null;			
		}
		return (Customer)list.get(0);
	}

	public Cart selectMyCart(int cartNo) {
		String query = "";
		return null;
	}
	
	//고객정보수정
	public int updateCustomer(String customerEmail2, Customer c) {
		String query = "update customer_tbl set customer_pw=?,customer_name=?,customer_phone=?,customer_email=? where customer_id=?";
		Object[] params = {c.getCustomerPw(),c.getCustomerName(),c.getCustomerPhone(),c.getCustomerEmail()+"@"+customerEmail2,c.getCustomerId()};
		int result = jdbc.update(query,params);
		return result;
	}
	//회원탈퇴
	public int deleteCustomer(int customerNo) {
		String query = "delete from customer_tbl where customer_no=?";
		Object[] params = {customerNo};
		int result = jdbc.update(query,params);
		return result;
	}
	//찜목록
	public List selectWishList(int customerNo, int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from product_like where customer_no=? order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, wishListRowMapper, customerNo, start, end);
		return list;
	}
	//고객리뷰 전체 수
	public int reviewTotalCount(String reviewWriter) {
		String query = "select count(*) from review_tbl where review_writer=?";
		int totalCount = jdbc.queryForObject(query, Integer.class, reviewWriter);
		return totalCount;
	}
	//고객리뷰리스트
	public List customerReviewList(String reviewWriter, int start, int end) {
		String query = "select * from (select rownum as rnum, r.* from (select review_no,order_no,product_no,review_writer,star_count,review_content,filepath,product_name,product_img from review_tbl join order_tbl using(order_no) join product_option_tbl using(product_option_no) join product_tbl using(product_no)where review_writer=? order by 1 desc)r) where rnum between ? and ?";
		List reviewList = jdbc.query(query, reviewListRowMapper,reviewWriter,start,end);
		return reviewList;
	}


}
