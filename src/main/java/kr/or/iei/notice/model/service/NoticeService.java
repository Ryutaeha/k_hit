package kr.or.iei.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.notice.model.dao.NoticeDao;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeListData;
@Service
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	public NoticeListData searchAllNotice(int reqPage) {
		int noticePage = 15;
		int end = reqPage * noticePage;
		int start = end - noticePage + 1;
		List noticeList = noticeDao.searchAllNotice(start,end);
		int totalCount = noticeDao.searchNoticeTotalCount();
		
		
		int totalPage = (int)Math.ceil(totalCount / (double)noticePage);
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
		
		NoticeListData nld = new NoticeListData(noticeList,pageNavi);
		return nld;
	}
	@Transactional
	public Notice selectOneNotice(int noticeNo) {
		int result = noticeDao.updateReadCount(noticeNo);
		if(result>0) {
			Notice n = noticeDao.selectOneNotice(noticeNo);
			return n;
		}else {			
			return null;
		}
	}

}
