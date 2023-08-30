package kr.or.iei.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chart {
	private String orderListDate;
	private int productNo;
	private int orderCount;
	private int orderState;
	private int orderListNo;
	private int productPrice;
	private int sellerNo;
}
