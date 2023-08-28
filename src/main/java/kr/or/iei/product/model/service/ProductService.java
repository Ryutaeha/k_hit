package kr.or.iei.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.product.model.dao.ProductDao;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	@Transactional
	public int deleteProduct(int productNo) {
		int result = productDao.deleteProduct(productNo);
		return result;
	}

	public Product getProduct(int productNo) {
		Product p = productDao.selectOneProduct(productNo);
		List productOptionList = productDao.selectProductOption(productNo);
		p.setProductOptionList(productOptionList);
		return p;
	}
}
