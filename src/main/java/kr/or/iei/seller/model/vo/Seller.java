package kr.or.iei.seller.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Seller {
	private int sellerNo;
	private String sellerId;
	private String sellerPw;
	private String sellerName;
	private String sellerImg;
	private String sellerPhone;
	private String sellerEmail;
	private String sellerIntroduce;
	private String sellerEnrollDate;
	private int memberCode;
	private List fileList;
	
}