package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelRefund {
	private int customerNo;
	private String orderListDate;
	private String productImg;
	private String productName;
	private String optionSize;
	private String optionColor;
	private int orderCount;
	private int orderState;
	private int productPrice;
}
