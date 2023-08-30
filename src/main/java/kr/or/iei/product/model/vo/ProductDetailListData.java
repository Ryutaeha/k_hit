package kr.or.iei.product.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDetailListData {
	private Product productList;
	private double avgStar;
	private String sellerName;
	private String sellerImg;
}
