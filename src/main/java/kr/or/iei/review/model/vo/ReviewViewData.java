package kr.or.iei.review.model.vo;

import java.util.List;

import kr.or.iei.product.model.vo.ReviewProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewViewData {
	private Review r;
	private ReviewProduct rp;
	private List commentList;
	private List reCommentList;
	private int reviewCount;
}
