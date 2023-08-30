package kr.or.iei.page.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewContentStar {
	private String reviewContent;
	private int starCount;
}
