package kr.or.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.seller.model.vo.Seller;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public List<Customer> customerList(int memberCode) {
		List list = adminDao.customerList(memberCode);
		return list;
	}

	public List<Seller> sellerList(int memberCode) {
		List list = adminDao.sellerList(memberCode);
		return list;
	}
}
