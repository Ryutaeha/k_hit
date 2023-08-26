package kr.or.iei.review.model.vo;

import kr.or.iei.product.model.vo.ReviewProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewListProduct {
	private int reviewNo;
	private int orderNo;
	private String reviewWriter;
	private int starCount;
	private String reviewContent;
	private String filepath;
	private String productName;
	private String productImg;
}
