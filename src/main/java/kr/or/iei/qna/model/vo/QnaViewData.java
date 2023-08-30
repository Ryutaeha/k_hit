package kr.or.iei.qna.model.vo;

import java.util.List;

import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.seller.model.vo.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaViewData {
	private Customer c;
	private Seller s;
	private List commentList;
	private List reCommenList;
}
