package kr.or.iei.seller.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellingDetail {
	private int sellerNo;
	private String orderListDate;
	private String productImg;
	private String productName;
	private String optionSize;
	private String optionColor;
	private int orderCount;
	private int orderState;
	private int productPrice;
	private int orderNo;
}
