package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
	private int customerNo;
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerPhone;
	private String customerEmail;
	private String customerEnrolldate;
	private int memberCode;
}
