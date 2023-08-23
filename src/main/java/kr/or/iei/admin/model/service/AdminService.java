package kr.or.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.customer.model.vo.Customer;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public List<Customer> memberList(int memberCode) {
		List list = adminDao.memberList(memberCode);
		return list;
	}
}
