package kr.or.iei.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.seller.model.service.SellerService;

@Controller
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
}
