package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
	private int addressNo;
	private int customerNo;
	private String addressName;
	private String addressPhone;
	private String addressPostalCode;
	private String addressSimple;
	private String addressDetail; 
}
