package kr.or.iei.review.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewComment {
	private int reviewCommentNo;
	private String reviewCommentWriter;
	private String reviewCommentContent;
	private String reviewCommentDate;
	private int reviewRef;
	private int reviewCommentRef;
}
