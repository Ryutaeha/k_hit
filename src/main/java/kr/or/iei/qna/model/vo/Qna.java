package kr.or.iei.qna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Qna {
	private int questionNo;
	private String questionTitle;
	private String questionContent;
	private String questionCusWriter;
	private String questionSellWriter;
	private String questionDate;
	private int questionReadCount;
}
