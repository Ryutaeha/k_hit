package kr.or.iei.seller.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductOptionListData;
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
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		List productList = sellerDao.selectProductList(sellerNo,start,end);
		int totalCount = sellerDao.selectProductTotalCount(sellerNo);
		int totalPage = (int)(Math.ceil(totalCount)/(double)numPerPage);
		int pageNaviSize = 5;
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

	public ProductListData addNewProductList(int sellerNo, int reqPage) {
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		List productList = sellerDao.addNewProductList(sellerNo,start,end);
		int totalCount = sellerDao.selectProductTotalCount(sellerNo);
		int totalPage = (int)(Math.ceil(totalCount)/(double)numPerPage);
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/seller/addNewProductList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>keyboard_double_arrow_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/seller/addNewProductList?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/seller/addNewProductList?reqPage="+(pageNo)+"'>";
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
			pageNavi += "<a class='page-item' href='/seller/addNewProductList?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>keyboard_double_arrow_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		ProductListData pld = new ProductListData(productList, pageNavi);
		return pld;
	}

	public ProductOptionListData productStockManagement(int sellerNo, int reqPage) {
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		List productOptionList = sellerDao.productStockManagement(sellerNo,start,end);
		int totalCount = sellerDao.selectProductOptionTotalCount(sellerNo);
		int totalPage = (int)(Math.ceil(totalCount)/(double)numPerPage);
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/seller/productStockManagement?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/seller/productStockManagement?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/seller/productStockManagement?reqPage="+(pageNo)+"'>";
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
			pageNavi += "<a class='page-item' href='/seller/productStockManagement?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		ProductOptionListData pold = new ProductOptionListData(productOptionList, pageNavi);
		return pold;
	}

	@Transactional
	public int changeOptionStock(int optionStock, int productOptionNo) {
		int result = sellerDao.changeOptionStock(optionStock, productOptionNo);
		return result;
	}
	@Transactional
	public int updateSeller(String customerEmail2, Seller s) {
		int result = sellerDao.updateSeller(customerEmail2,s);
		return result;
	}

	@Transactional
	public int addNewProduct(Product p, int sellerNo, String[] optionSize, String[] optionColor) {
		int result = sellerDao.addNewProduct(p, sellerNo);
		int productNo = sellerDao.getProductNo();
		
		for(int i=0;i<optionColor.length;i++) {
			ProductOption productOption = new ProductOption();
			productOption.setOptionSize(optionSize[i]);
			productOption.setOptionColor(optionColor[i]);
			productOption.setProductNo(productNo);
			result += sellerDao.addNewProductOption(productOption);
			
		}
		return result;
	}
	//판매자 리뷰 전체 수
	public int reviewTotalCount(int sellerNo) {
		int totalCount = sellerDao.reviewTotalCount(sellerNo);
		return totalCount;
	}
	//판매자 리뷰 리스트
	public List sellerReviewList(int sellerNo, int start, int end) {
		List reviewList = sellerDao.sellerReviewList(sellerNo,start,end);
		return reviewList;
	}

	public Seller selectSellerId(String sellerId) {
		Seller s = sellerDao.selectSellerId(sellerId);
		return s;
	}
}
