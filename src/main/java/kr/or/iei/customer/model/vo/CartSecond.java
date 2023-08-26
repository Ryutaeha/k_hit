package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartSecond {
	   private int productName;
	   private String optionSize;
	   private String optionColor;
	   private int cartCount;
	   private String productPrice;
}
