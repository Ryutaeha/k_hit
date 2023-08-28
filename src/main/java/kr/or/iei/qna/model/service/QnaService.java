package kr.or.iei.qna.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.qna.model.dao.QnaDao;

@Service
public class QnaService {
	@Autowired
	private QnaDao qnaDao;

	public List searchAllQna() {
		List qnaList = qnaDao.searchAllQna();
		return qnaList;
	}
}
