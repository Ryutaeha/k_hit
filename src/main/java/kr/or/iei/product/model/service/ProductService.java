package kr.or.iei.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
import kr.or.iei.product.controller.ProductDetail;
import kr.or.iei.product.controller.ProductDetailList;
import kr.or.iei.product.controller.ProductDetailListData;
*/
import kr.or.iei.product.model.dao.ProductDao;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductDetailListData;
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

	public int updateProduct(Product p, String[] optionSize, String[] optionColor) {
		//, int delFileNo 매개변수 지워둠. 확인필요
		int result = productDao.updateProduct(p);
		System.out.println(optionColor);
		
		if(optionSize != null) {
			for(int i=0;i<optionColor.length;i++) {
				ProductOption productOption = new ProductOption();
				productOption.setOptionSize(optionSize[i]);
				productOption.setOptionColor(optionColor[i]);
				productOption.setProductNo(p.getProductNo());
				result += productDao.updateProduct(productOption);
			}
			
		}
		/*
		if(result > 0) {
			if(delFileNo > 0) {
				//삭제파일이 있는 경우에만 진행.(삭제파일 조회 -> 삭제파일 삭제)
				//삭제파일 없는데 for문 돌리면 에러남.

					Filepath noticeFile = noticeDao.selectOneFile(fileNo);
					delFileList.add(noticeFile);
					//db에서 파일 삭제
					result += productDao.deleteFile(filepath);
		}
		*/
		return result;
	}

	public int changeStockStatus(int StockStatus, int productOptionNo) {
		int result = productDao.changeStockStatus(StockStatus, productOptionNo);
		return result;
	}

	public ProductDetailListData productDetail(int productNo) {
		Product productList = productDao.ProductDetailListData(productNo);
		double avgStar = productDao.averageStar(productNo);
		List productOptionList = productDao.ProductOptionDetailListData(productNo);
		productList.setProductOptionList(productOptionList);
		System.out.println(productOptionList);
		ProductDetailListData pdld = new ProductDetailListData(productList,avgStar);
		return pdld;
	}

	

}
