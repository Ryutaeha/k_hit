package kr.or.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.seller.model.vo.Seller;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public List<Customer> customerList(String input) {
		List list = adminDao.customerList(input);
		return list;
	}

	public List<Seller> sellerList(String input) {
		List list = adminDao.sellerList(input);
		return list;
	}

	public List<Category> category() {
		List category = adminDao.category();
		return category;
	}

	public List<Product> product(String input, int categoryNo, int productCheck) {
		List product;
		if(categoryNo==0) {
			product = adminDao.product(input,productCheck);
		}else {
			product = adminDao.product(input,categoryNo,productCheck);			
		}
		return product;
	}
	
}
