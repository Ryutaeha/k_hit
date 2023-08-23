package kr.or.iei.seller.model.vo;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductListData {
	private List productList;
	private String pageNavi;
}
