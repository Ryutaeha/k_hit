package kr.or.iei.notice.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeListData {
	private List noticeList;
	private String pagaNavi;
}
