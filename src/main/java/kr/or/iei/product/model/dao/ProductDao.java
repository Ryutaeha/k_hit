package kr.or.iei.product.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductOptionRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private ProductOptionRowMapper productOptionRowMapper;
	
	public int deleteProduct(int productNo) {
		String query = "UPDATE PRODUCT_TBL SET PRODUCT_CHECK='4' WHERE product_no=?";
		Object[] params = {productNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public Product selectOneProduct(int productNo) {
		String query = "select * from product_tbl where product_no=?";
		List list = jdbc.query(query, productRowMapper,productNo);
		return (Product)list.get(0);
	}

	public List selectProductOption(int productNo) {
		String query = "select * from product_option_tbl where product_no=?";
		List list = jdbc.query(query, productOptionRowMapper,productNo);
		return list;
	}

	public int updateProduct(Product p) {
		String query = "update product_tbl set product_name=?,product_img=?,product_price=?,product_content=?,product_content_details=?,category_no=? where product_no=?";
		Object[] params = {p.getProductName(),p.getProductImg(),p.getProductPrice(),p.getProductContent(),p.getProductContentDetails(),p.getCategoryNo(),p.getProductNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int updateProduct(ProductOption productOption) {
		String query = "update product_option_tbl set option_size=?,option_color=? where product_no=?";
		Object[] params = {productOption.getOptionSize(),productOption.getOptionColor(),productOption.getProductNo()};
		int result = jdbc.update(query,params);
		return result;
	}


}
