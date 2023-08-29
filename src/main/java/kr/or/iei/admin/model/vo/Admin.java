package kr.or.iei.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
	int memberCode;
	String adminId;
	String adminPw;
	String adminPhone;
	String adminImg;
}
