package kr.or.iei.seller.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerFile {
	private int sellerFileNo;
	private int sellerNo;
	private String sellerFilename;
	private String sellerFilepath;
}
