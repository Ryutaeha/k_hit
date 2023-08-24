package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
	private int orderNo;
	private int orderListNo;
	private int addressNo;
	private int productOptionNo;
	private int orderCount;
	private int orderState;
	private String orderRequest;
}
