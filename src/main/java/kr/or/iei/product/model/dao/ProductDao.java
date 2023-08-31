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
		String query = "insert into PRODUCT_OPTION_TBL values(PRODUCT_OPTION_SEQ.nextval,?,?,?,default,0)";
		Object[] params = {productOption.getProductNo(),productOption.getOptionSize(),productOption.getOptionColor()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int changeStockStatus(int StockStatus, int productOptionNo) {
		String query = "update product_option_tbl set out_of_stock=? where product_option_no=?";
		Object[] params = {StockStatus, productOptionNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public Product ProductDetailListData(int productNo) {
		String query = "select * from product_tbl where product_no=?";
		List list = jdbc.query(query, productRowMapper,productNo);
		return (Product)list.get(0);
	}

	public double averageStar(int productNo) {
		//System.out.println("avg : "+productNo);
		String query = "select nvl(avg(r.star_count),0) from (select star_count,product_no from product_option_tbl join order_tbl using(product_option_no) join review_tbl using(order_no) where product_no=?)r";
		double avgStar = jdbc.queryForObject(query, Double.class,productNo);
		return avgStar;
	}

	public List ProductOptionDetailListData(int productNo) {
		String query = "select * from product_option_tbl where product_no=?";
		List list = jdbc.query(query, productOptionRowMapper,productNo);
		return list;
	}

	public String getSellerName(int productNo) {
		String query = "select seller_name from seller_tbl join product_tbl using(seller_no) where product_no=?";
		String sellerName = jdbc.queryForObject(query, String.class,productNo);
		return sellerName;
	}
	
	public String getSellerImg(int productNo) {
		String query = "select seller_img from seller_tbl join product_tbl using(seller_no) where product_no=?";
		String sellerImg = jdbc.queryForObject(query, String.class,productNo);
		return sellerImg;
	}

	public int addCart(int productOptionNo, int selectOptionStock, int customerNo) {
		String query = "INSERT INTO CART_TBL VALUES(CART_SEQ.NEXTVAL,?,?,?)";
		Object[] params = {customerNo,productOptionNo,selectOptionStock};
		int result = jdbc.update(query,params);
		return result;
	}

}
