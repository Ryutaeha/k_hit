package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOptionSecond {
	private int productOptionNo;
	private int productNo;
	private String optionSize;
	private String optionColor;
	private int optionStock;
	private int outOfStock;
	//판매상품 재고관리를 위해 추가한 부분
	private String productName;
}
