package kr.or.iei.seller.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.seller.model.dao.SellerDao;

@Service
public class SellerService {
	@Autowired
	private SellerDao sellerDao;

}
