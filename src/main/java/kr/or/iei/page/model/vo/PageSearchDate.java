package kr.or.iei.page.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageSearchDate {
	private List searchList;
	private int searchCount;
}
