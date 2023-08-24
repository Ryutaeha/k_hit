package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDetail {
	private String sellerId;
	private int productNo;
	private int sellerNo;
	private String productName;
	private String productImg;
	private int productPrice;
	private String productContent;
	private String productContentDetails;
	private String productRegDate;
	private int productCheck;
	private int categoryNo;
	private String categoryName;
}
