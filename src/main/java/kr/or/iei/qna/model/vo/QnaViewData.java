package kr.or.iei.qna.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaViewData {
	private Qna q;	
	private List commentList;
}
