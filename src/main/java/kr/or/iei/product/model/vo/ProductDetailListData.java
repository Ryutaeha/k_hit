package kr.or.iei.product.model.vo;

import java.util.List;

import kr.or.iei.review.model.vo.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDetailListData {
	private Product productList;
	private double avgStar;
	private String sellerName;
	private String sellerImg;
}
