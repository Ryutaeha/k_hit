package kr.or.iei.qna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaComment{
	private int questionCommentNo; 
	private int questionNo; 
	private String questionWriter;
	private String questionContent; 
	private String questionDate;
} 

