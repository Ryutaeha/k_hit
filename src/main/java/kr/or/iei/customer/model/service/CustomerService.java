package kr.or.iei.customer.model.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import kr.or.iei.customer.model.dao.CustomerDao;
import kr.or.iei.customer.model.vo.Address;
import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;
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
	

	public int reviewTotalCount(String reviewWriter) {
		int totalCount = customerDao.reviewTotalCount(reviewWriter);
		return totalCount;
	}
	public List customerReviewList(String reviewWriter, int start, int end) {
		List reviewList = customerDao.customerReviewList(reviewWriter, start,end);
		return reviewList;
	}

	public List selectCartList(int customerNo) {
		List cartList = customerDao.selectCartList(customerNo);
		return cartList;
	}
	public List selectOrderList(int customerNo) {
		List old = customerDao.selectOrderList(customerNo);
		return old;

	}

	public int cartDelete(int cartNo) {
		int result = customerDao.cartDelete(cartNo);
		return result;
	}

	public List selectCancelRefundList(int customerNo) {
		List crl = customerDao.selectCancelRefundList(customerNo);
		return crl;

	}
	public Address selectAddressNo(int customerNo) {
		Address address = customerDao.selectAddressNo(customerNo);
		return address;
	}
	@Transactional
	public int insertDeliver(Address a) {
		int result = customerDao.insertDeliver(a);
		return result;
	}
	public List cancelRefundapplication(int customerNo,int orderNo) {
		List cr = customerDao.cancelRefundapplication(customerNo,orderNo);
		return cr;
	}
	@Transactional
	public int updateDeliver(Address a) {
		int result = customerDao.updateDeliver(a);
		return result;
	}

}

