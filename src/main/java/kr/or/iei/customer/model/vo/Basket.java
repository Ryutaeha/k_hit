package kr.or.iei.customer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Basket {
	private int basketNo;
	private int customerNo;
	private int	productOptionNo;
	private int	basketCount;
}
