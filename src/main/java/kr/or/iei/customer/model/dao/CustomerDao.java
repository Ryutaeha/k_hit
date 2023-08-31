package kr.or.iei.customer.model.dao;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.customer.model.vo.Address;
import kr.or.iei.customer.model.vo.AdressRowMapper;
import kr.or.iei.customer.model.vo.CancelRefundListRowMapper;
import kr.or.iei.customer.model.vo.CancelRefundRowMapper;
import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.CartList;
import kr.or.iei.customer.model.vo.CartListRowMapper;
import kr.or.iei.customer.model.vo.CartRowMapper;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.CustomerRowMapper;
import kr.or.iei.customer.model.vo.OrderDetailRowMapper;
import kr.or.iei.review.model.vo.ReviewListRowMapper;

@Repository
public class CustomerDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CustomerRowMapper customerRowMapper;
	@Autowired
	private ReviewListRowMapper reviewListRowMapper;
	@Autowired 
	private CartListRowMapper cartListRowMapper;
	@Autowired
	private OrderDetailRowMapper orderDetailRowMapper;
	@Autowired
	private CancelRefundListRowMapper cancelRefundListRowMapper;
	@Autowired
	private AdressRowMapper addressRowMapper;
	@Autowired 
	private CancelRefundRowMapper cancelRefundRowMapper;
	@Autowired
	private CartRowMapper cartRowMapper;


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

	public int selectWisiListTotalCount(int customerNo) {
		String query = "select count(*) as cnt from product_like where customer_no=?";
		int totalCount = jdbc.queryForObject(query, Integer.class,customerNo);
		return totalCount;
	}
	
	//장바구니 리스트
	public List selectCartList(int customerNo) {
		String query = "select cart_no,product_img,product_name,option_size,option_color,product_price,cart_count from cart_tbl join product_option_tbl using(product_option_no) join product_tbl using(product_no) where customer_no = ?";
		List list = jdbc.query(query, cartListRowMapper, customerNo);
		return list;
	}

	public List selectOrderList(int customerNo) {
		String query ="select     a.customer_no,    o.order_no,    ol.order_list_date,     p.product_img,     p.product_name,     op.option_size,     op.option_color,     o.order_count,    p.product_price,    o.order_state from order_list_tbl ol join order_tbl o on ol.order_list_no = o.order_list_no join product_option_tbl op on o.product_option_no = op.product_option_no join product_tbl p on p.product_no = op.product_no join address_tbl a on o.address_no = a.address_no where customer_no=? and not order_state in ('5','6')";
		List orderList = jdbc.query(query,orderDetailRowMapper,customerNo);
		return orderList;
	}


	public int cartDelete(int cartNo) {
		String query = "delete from cart_tbl where cart_no=?";
		int result = jdbc.update(query,cartNo);
		return result;
	}

	public List selectCancelRefundList(int customerNo) {
		String query ="select a.customer_no, o.order_no, ol.order_list_date, p.product_img, p.product_name, op.option_size, op.option_color, o.order_count,p.product_price,o.order_state from order_list_tbl ol join order_tbl o on ol.order_list_no = o.order_list_no join product_option_tbl op on o.product_option_no = op.product_option_no join product_tbl p on p.product_no = op.product_no join address_tbl a on o.address_no = a.address_no where customer_no=? and(order_state=5 or order_state=6)";
		List cancelRefundList = jdbc.query(query,cancelRefundListRowMapper,customerNo);
		return cancelRefundList;

	}

	public Address selectAddressNo(int customerNo) {
		String query = "select * from address_tbl where customer_no=?";
		List list = jdbc.query(query, addressRowMapper, customerNo);
		if(list.isEmpty()) {
			return null;
		}
		return (Address)list.get(0);
	}

	public int insertDeliver(Address a) {
		String query = "insert into address_tbl values(address_seq.nextval,?,?,?,?,?,?)";
		Object[] params = {a.getCustomerNo(),a.getAddressName(),a.getAddressPhone(),a.getAddressPostalCode(),a.getAddressSimple(),a.getAddressDetail()};
		int result = jdbc.update(query,params);
		return result;
	}

	public List cancelRefundapplication(int customerNo,int orderNo) {
		String query = "select a.customer_no, o.order_no, ol.order_list_date, p.product_img, p.product_name, op.option_size, op.option_color, o.order_count,p.product_price,o.order_state from order_list_tbl ol join order_tbl o on ol.order_list_no = o.order_list_no join product_option_tbl op on o.product_option_no = op.product_option_no join product_tbl p on p.product_no = op.product_no join address_tbl a on o.address_no = a.address_no where order_no=? and(order_state=1 or order_state=3)";
		List cr = jdbc.query(query, cancelRefundRowMapper, orderNo);
		System.out.println(cr);
		System.out.println(orderNo+"  "+customerNo);
		return cr;
	}

	//배송정보 수정
	public int updateDeliver(Address a) {
		String query = "update address_tbl set address_name=?,address_phone=?,address_postal_code=?,address_simple=?,address_detail=? where customer_no=?";
		Object[] params = {a.getAddressName(),a.getAddressPhone(),a.getAddressPostalCode(),a.getAddressSimple(),a.getAddressDetail(),a.getCustomerNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int insertOrderList() {
		String query = "insert into order_list_tbl values(order_list_seq.nextval,to_char(sysdate,'yyyy-mm-dd'),0)";
		int result = jdbc.update(query);
		return result;
	}
	//최신 총주문번호 구하기
	public int getOrderList() {
		String query = "select max(order_list_no) from order_list_tbl";
		int orderListNo = jdbc.queryForObject(query, Integer.class);
		return orderListNo;
	}

	public Cart searchCartOrder(int i) {
		String query = "select * from cart_tbl where cart_no=?";
		List list = jdbc.query(query, cartRowMapper, i);
		if(list.isEmpty()) {
			return null;
		}
		return (Cart)list.get(0);
	}
	//개별주문 넣기
	public int insertOrder(Cart c, int orderListNo, int addressNo) {
		String query = "insert into order_tbl values(order_seq.nextval,?,?,?,?,1)";
		Object[] params = {orderListNo,addressNo,c.getProductOptionNo(),c.getCartCount()};
		int result = jdbc.update(query,params);
		return result;
	}
	
	

}
