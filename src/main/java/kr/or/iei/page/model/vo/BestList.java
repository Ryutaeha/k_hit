package kr.or.iei.page.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BestList {
	private int bestList;
	private int productNo;
	private String productName;
	private String productContent;
	private String productContentDetails;
	private String productImg;
	private int productPrice;
	private int orderCount;
}
