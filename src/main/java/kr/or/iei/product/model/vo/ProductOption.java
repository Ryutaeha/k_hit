package kr.or.iei.product.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOption {
	private int productOptionNo;
	private int productNo;
	private String optionSize;
	private String optionColor;
	private int optionStock;

}
