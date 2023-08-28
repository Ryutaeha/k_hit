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
		//검색리스트
		List productList = pageDao.searchProduct(searchWord);
		//String searchCount = pageDao.searchProductCount(searchWord);
		//검색갯수
		int searchCount = productList.size();
		//검색된 항목하나하나의 리뷰내용
		//List reviewContent = pageDao.reveiwContent(searchWord);
		String reviewContent = null;
		
		PageSearchDate psd = new PageSearchDate(productList,searchCount,reviewContent);
		System.out.println("검색 갯수 : "+psd.getSearchList().size());
		
		return psd;
	}

	public List searchNewList() {
		List newPrd = pageDao.searchNewList();
		return newPrd;
	}

	public List searchAll() {
		List allPrd = pageDao.searchAll();
		return allPrd;
	}

	public List searchEarring() {
		List list = pageDao.searchEarring();
		return list;
	}

	public List searchNecklace() {
		List list = pageDao.searchNecklace();
		return list;
	}

	public List searchRing() {
		List list = pageDao.searchRing();
		return list;
	}

	public List searchBracelet() {
		List list = pageDao.searchBracelet();
		return list;
	}

	public List searchWatch() {
		List list = pageDao.searchWatch();
		return list;
	}

	public List searchHair() {
		List list = pageDao.searchHair();
		return list;
	}

	public List searchOther() {
		List list = pageDao.searchOther();
		return list;
	}
}
