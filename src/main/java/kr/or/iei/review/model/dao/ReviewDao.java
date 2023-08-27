package kr.or.iei.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.ReviewProduct;
import kr.or.iei.product.model.vo.ReviewProductRowMapper;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewListRowMapper;
import kr.or.iei.review.model.vo.ReviewRowMapper;

@Repository
public class ReviewDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReviewRowMapper reviewRowMapper;
	@Autowired
	private ReviewProductRowMapper reviewProductRowMapper;
	@Autowired
	private ReviewListRowMapper reviewListRowMapper;
	
	public int insertReview(Review review) {
		String query = "insert into review_tbl values(review_seq.nextval,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),default)";
		Object[] params = {review.getOrderNo(),review.getReviewWriter(),review.getStarCount(),review.getReviewContent(),review.getFilepath()};
		int result = jdbc.update(query,params);
		return result;
	}
	
	//개별주문번호 가지고 개별주문번호,상품 이미지,옵션,상품이름 가져오기
	public ReviewProduct selectReviewProduct(int orderNo) {
		String query = "select order_no,product_name,option_size,option_color,product_img from order_tbl join product_option_tbl using(product_option_no) join product_tbl using(product_no) where order_no=?";
		List list = jdbc.query(query,reviewProductRowMapper,orderNo);
		if(list.isEmpty()) {
			return null;
		}
		return (ReviewProduct)list.get(0);
	}
	//리뷰전체수
	public int reviewTotalCount() {
		String query = "select count(*) from review_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	//리뷰리스트
	public List selectReviewList(int start, int end) {
		String query = "select * from (select rownum as rnum, r.* from (select review_no,order_no,product_no,review_writer,star_count,review_content,filepath,product_name,product_img from review_tbl join order_tbl using(order_no) join product_option_tbl using(product_option_no) join product_tbl using(product_no)order by 1 desc)r) where rnum between ? and ?";
		List reviewList = jdbc.query(query, reviewListRowMapper, start, end);
		return reviewList;
	}

}
