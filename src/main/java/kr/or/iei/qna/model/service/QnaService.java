package kr.or.iei.qna.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.qna.model.dao.QnaDao;
import kr.or.iei.qna.model.vo.QnaListData;

@Service
public class QnaService {
	@Autowired
	private QnaDao qnaDao;

	public QnaListData searchAllQna(int reqPage) {
		//1페이지당 게시물 지정 : 15개
		int qnaPage = 15;
		int end = reqPage * qnaPage;
		int start = end - qnaPage + 1;
		List qnaList = qnaDao.searchAllQna(start,end);
		//2.페이지네비게이션 제작
		//총페이지 수 계산을 위해선 총 게시물 수를 알아야함->db에서 그룹함수로 조회
		int totalCount = qnaDao.searchQnaTotalCount();
		
		int totalPage = (int)Math.ceil(totalCount / (double)qnaPage);
		//페이지 네비게이션 사이즈 지정
		int pageNaviSize = 5;
		//페이지 네비게이션 시작번호
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		//이전버튼 제작
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qna/qnaList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/qna/qnaList?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/qna/qnaList?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/qna/qnaList?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		QnaListData qld = new QnaListData(qnaList,pageNavi);
		
		return qld;
	}
}
