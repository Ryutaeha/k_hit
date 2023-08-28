package kr.or.iei.review.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.ReviewProduct;
import kr.or.iei.product.model.vo.ReviewProductRowMapper;
import kr.or.iei.review.model.vo.Review;
import kr.or.iei.review.model.vo.ReviewComment;
import kr.or.iei.review.model.vo.ReviewCommentRowMapper;
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
	@Autowired
	private ReviewCommentRowMapper reviewCommentRowMapper;
	
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

	public Review selectOneReview(int reviewNo, int customerNo) {
		String query = "select r.*, (select count(*) from review_like where review_no=r.review_no and customer_no=?) as is_like, (select count(*) from review_like where review_no=r.review_no) as like_count from (select * from review_tbl where review_no=?)r";
		List list = jdbc.query(query, reviewRowMapper,customerNo, reviewNo);
		if(list.isEmpty()) {
			return null;
		}
		return (Review)list.get(0);
	}

	public int updateReadCount(int reviewNo) {
		String query = "update review_tbl set read_count = read_count+1 where review_no=?";
		int result = jdbc.update(query,reviewNo);
		return result;
	}

	public int insertReviewLike(int reviewNo, int customerNo) {
		String query = "insert into review_like values(?,?)";
		Object[] params = {reviewNo,customerNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int likeCount(int reviewNo) {
		String query = "select count(*) from review_like where review_no=?";
		int likeCount = jdbc.queryForObject(query, Integer.class, reviewNo);
		return likeCount;
	}

	public int removeReviewLike(int reviewNo, int customerNo) {
		String query = "delete from review_like where review_no=? and customer_no=?";
		Object[] params = {reviewNo,customerNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public int insertComment(ReviewComment rc) {
		String query = "insert into review_comment values(review_comment_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		String reviewCommentRef = rc.getReviewCommentRef()==0?null:String.valueOf(rc.getReviewCommentRef());
		Object[] params = {rc.getReviewCommentWriter(),rc.getReviewCommentContent(),rc.getReviewRef(),reviewCommentRef};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectCommentList(int reviewNo) {
		String query = "select * from review_comment where review_ref=? and review_comment_ref is null order by 1";
		List list = jdbc.query(query, reviewCommentRowMapper, reviewNo);
		return list;
	}

	public List selectReCommentList(int reviewNo) {
		String query = "select * from review_comment where review_ref=? and review_comment_ref is not null order by 1";
		List list = jdbc.query(query, reviewCommentRowMapper, reviewNo);
		return list;
	}

	public int selectReviewCount(int reviewNo) {
		String query = "select count(*) from review_comment where review_ref=?";
		int reviewCount = jdbc.queryForObject(query, Integer.class, reviewNo);
		return reviewCount;
	}

	public int deleteComment(int reviewCommentNo) {
		String query = "delete from review_comment where review_comment_no=?";
		int result = jdbc.update(query,reviewCommentNo);
		return result;
	}

}
