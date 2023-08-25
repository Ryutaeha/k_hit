package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddNewProduct {
	private int productNo;
	private int sellerNo;
	private String productName;
	private String productImg;
	private int productPrice;
	private String productContent;
	private String productContentDetails;
	private String productRegDate;
	private int categoryNo;
	//상품 등록으로 추가되는 부분
	private int productOptionNo;
	private String[] optionSize;
	private String[] optionColor;
	private int optionStock;
}
