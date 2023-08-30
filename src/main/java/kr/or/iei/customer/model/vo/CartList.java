package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartList {
	private int cartNo;
	private String productImg;
	private String productName;
	private String optionSize;
	private String optionColor;
	private int productPrice;
	private int cartCount;
}
