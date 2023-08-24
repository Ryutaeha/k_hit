package kr.or.iei.customer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.customer.model.dao.CustomerDao;
import kr.or.iei.customer.model.vo.Cart;
import kr.or.iei.customer.model.vo.Customer;

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

}
