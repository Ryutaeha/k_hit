package kr.or.iei.page.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.page.model.dao.PageDao;

@Service
public class PageService {
	@Autowired
	private PageDao pageDao;

	public List searchProduct(String productName) {
		List productList = pageDao.searchProduct(productName);
		return productList;
	}
}
