package kr.or.iei.product.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOptionListData {
	private List productOptionList;
	private String pageNavi;
	//재고관리 페이지 페이지번호 넣을지 고민중
}
