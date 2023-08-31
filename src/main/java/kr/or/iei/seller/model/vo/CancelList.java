package kr.or.iei.seller.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CancelList {
	private int orderNo;
	private int orderListNo;
	private int addressNo;
	private int productOptionNo;
	private int orderCount;
	private int orderState;
	private String productName;
	private String productImg;
}
