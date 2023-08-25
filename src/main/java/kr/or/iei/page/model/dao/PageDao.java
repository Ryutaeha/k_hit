package kr.or.iei.page.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PageDao {
	@Autowired
	private JdbcTemplate jdbc;
	
	
	public List searchProduct(String productName) {
		//가져와야되는 정보: productNo,sellerNo,productName,productImg,productPrice,categoryNo
		List prodcutList = null;
		return prodcutList;
	}

}
