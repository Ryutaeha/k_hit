package kr.or.iei.product.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	private int productNo;
	private int sellerNo;
	private String productName;
	private String productImg;
	private int productPrice;
	private String productContent;
	private String productContentDetails;
	private String productRegDate;
	private int productCheck;
	private int categoryNo;
	
	private List productOptionList;
}
