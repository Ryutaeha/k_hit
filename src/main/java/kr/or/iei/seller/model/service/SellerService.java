package kr.or.iei.seller.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.seller.model.dao.SellerDao;
import kr.or.iei.seller.model.vo.Seller;

@Service
public class SellerService {
	@Autowired
	private SellerDao sellerDao;
	
	@Transactional
	public int insertSeller(Seller s,String customerEmail2) {
		int result = sellerDao.insertSeller(s,customerEmail2);
		return result;
	}

}
