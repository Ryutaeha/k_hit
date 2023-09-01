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
	//좋아요
	//private int isLike;
	
	//옵션은 종속된거라 여기 생성
	private List productOptionList;
	//리뷰목록 8/31...생성해도 되나?
	private List reviewList;
}
