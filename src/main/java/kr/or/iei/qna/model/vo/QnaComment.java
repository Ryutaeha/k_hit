package kr.or.iei.qna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data	
public class QnaComment {
	private int qnaCommentNo;
	private String qnaCommentWriter;
	private String qnaCommentContent;
	private String qnaCommentDate;
	private int qnaRef;
	private int qnaCommentRef;	
}
