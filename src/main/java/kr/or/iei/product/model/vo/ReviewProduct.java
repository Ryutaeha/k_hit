package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewProduct{
	private int orderNo;
	private String productName;
	private String optionSize;
	private String optionColor;
	private String productImg;
	

}
