package kr.or.iei.customer.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.customer.model.dao.CustomerDao;
import kr.or.iei.customer.model.vo.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional
	public int insertCustomer(Customer customer) {
		int result = customerDao.insertCustomer(customer);
		return result;
	}

	public Customer selectCustomerId(String customerId) {
		Customer c = customerDao.selectCustomerId(customerId);
		return c;
	}

}
