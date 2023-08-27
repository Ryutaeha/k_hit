package kr.or.iei.page.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.page.model.dao.PageDao;
import kr.or.iei.page.model.vo.PageSearch;
import kr.or.iei.page.model.vo.PageSearchDate;

@Service
public class PageService {
	@Autowired
	private PageDao pageDao;

	public PageSearchDate searchProduct(String searchWord) {
		List productList = pageDao.searchProduct(searchWord);
		//String searchCount = pageDao.searchProductCount(searchWord);
		int searchCount = productList.size();
		PageSearchDate psd = new PageSearchDate(productList,searchCount);
		System.out.println("검색 갯수 : "+psd.getSearchList().size());
		
		return psd;
	}
}
