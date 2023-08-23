package kr.or.iei.seller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.seller.model.dao.SellerDao;
import kr.or.iei.seller.model.vo.Seller;
import kr.or.iei.seller.model.vo.ProductListData;

@Service
public class SellerService {
	@Autowired
	private SellerDao sellerDao;
	
	@Transactional
	public int insertSeller(Seller s,String customerEmail2) {
		int result = sellerDao.insertSeller(s,customerEmail2);
		return result;
	}

	public ProductListData selectProductList(int sellerNo, int reqPage) {
		int numPerPage = 2;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		List productList = sellerDao.selectProductList(sellerNo,start,end);
		int totalCount = sellerDao.selectProductTotalCount(sellerNo);
		int totalPage = (int)(Math.ceil(totalCount)/(double)numPerPage);
		System.out.println(totalPage);
		System.out.println(totalCount);
		int pageNaviSize = 2;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/seller/productManagement?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/seller/productManagement?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/seller/productManagement?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/seller/productManagement?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		ProductListData pld = new ProductListData(productList, pageNavi);
		return pld;
	}

	public Seller selectOneSeller(String sellerSignId, String sellerSignPw) {
		Seller s = sellerDao.selectOneSeller(sellerSignId,sellerSignPw);
		return s;
	}

}
