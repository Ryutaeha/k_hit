package kr.or.iei.customer.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import kr.or.iei.customer.model.dao.CustomerDao;
import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.customer.model.vo.WishListData;
import kr.or.iei.seller.model.vo.ProductListData;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional
	//고객회원가입
	public int insertCustomer(Customer customer, String customerEmail2) {
		int result = customerDao.insertCustomer(customer, customerEmail2);
		return result;
	}
	//고객아이디 찾기
	public Customer selectCustomerId(String customerId) {
		Customer c = customerDao.selectCustomerId(customerId);
		return c;
	}
	//고객 로그인
	public Customer selectOneCustomer(String customerSignId, String customerSignPw) {
		Customer c = customerDao.selectOneCustomer(customerSignId,customerSignPw);
		return c;
	}
	//장바구니 가져오기
	public Cart selectMyCart(int cartNo) {
		Cart cart = customerDao.selectMyCart(cartNo);
		return cart;
	}
	//회원정보수정
	@Transactional
	public int updateCustomer(String customerEmail2, Customer c) {
		int result = customerDao.updateCustomer(customerEmail2,c);
		return result;
	}
	//회원탈퇴
	@Transactional
	public int deleteCustomer(int customerNo) {
		int result = customerDao.deleteCustomer(customerNo);
		return result;
	}
	public WishListData selectWishList(int customerNo, int reqPage) {
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		List wishList = customerDao.selectWishList(customerNo,start,end);
		int totalCount = customerDao.selectWisiListTotalCount(customerNo);
		int totalPage = (int)(Math.ceil(totalCount)/(double)numPerPage);
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/customer/wishList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/customer/wishList?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/customer/wishList?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/customer/wishList?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		WishListData wld = new WishListData(wishList, pageNavi);
		return wld;
	}


	public int reviewTotalCount(String reviewWriter) {
		int totalCount = customerDao.reviewTotalCount(reviewWriter);
		return totalCount;
	}
	public List customerReviewList(String reviewWriter, int start, int end) {
		List reviewList = customerDao.customerReviewList(reviewWriter, start,end);
		return reviewList;
	}
	public List selectOrderList(int customerNo) {
		List old = customerDao.selectOrderList(customerNo);
		return old;
	}
	public List selectCancelRefundList(int customerNo) {
		List crl = customerDao.selectcanCelList(customerNo);
		return crl;
	}
}

