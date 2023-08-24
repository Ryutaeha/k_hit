package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderList {
	private int orderListNo;
	private String orderListDate;
	private int orderListCost;
}
