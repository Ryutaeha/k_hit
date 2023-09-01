package kr.or.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.customer.model.vo.Customer;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.ProductDetail;
import kr.or.iei.seller.model.vo.Seller;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public List<Customer> customerList(String input) {
		List list = adminDao.customerList(input);
		return list;
	}

	public List<Seller> sellerList(String input) {
		List list = adminDao.sellerList(input);
		return list;
	}

	public List<Category> category() {
		List category = adminDao.category();
		return category;
	}

	public List<ProductDetail> product(String input, int categoryNo, int productCheck) {
		List product;
		if(categoryNo==0) {
			product = adminDao.product(input,productCheck);
		}else {
			product = adminDao.product(input,categoryNo,productCheck);			
		}
		return product;
	}
	public List<ProductDetail> product(int pNo) {
		List product;
			product = adminDao.product(pNo);
		return product;
	}

	public Seller selectSeller(String sId) {
		return adminDao.selectSeller(sId);
	}

	public Customer selectCustomer(String cId) {
		return adminDao.selectCustomer(cId);
	}

	public Admin AdminLogin(String adminSignId, String adminSignPw) {
		return adminDao.adminLogin(adminSignId,adminSignPw);
	}
	@Transactional
	public int insertNotice(Notice n) {
		return adminDao.insertNotice(n);
	}

	public List<Notice> noticeList(int noticeFix, String input) {
		if(input.equals("")) {
			input = "%";
		}
		if(noticeFix==1 ||noticeFix ==0) {
			return adminDao.noticeList(noticeFix,input);			
		}else {
			return adminDao.noticeList(input);			

		}
		
	}

	public Notice noticeView(int nNo) {
		return adminDao.noticeView(nNo);
	}
	
	@Transactional
	public int productCheckChange(int productCheck, int productNo) {
		return adminDao.productCheckChange(productCheck, productNo);
	}
	@Transactional
	public int fix(int fix, int nNo) {
		return adminDao.fix(fix,nNo);
	}

	public List salesList() {
		return adminDao.salesList();
	}

	public List salesListTest() {
		return adminDao.salesListTest();
	}

	public List question() {
		return adminDao.question();
	}

	public List selectQna(String qNo) {
		return adminDao.selectQna(qNo);
	}

	public List selectSellerP(String sId) {
		return adminDao.selectSellerP(sId);
	}

	public List productO(int pNo) {
		return adminDao.productO(pNo);
	}

	public List selectCustomerB(String cId) {
		return adminDao.selectCustomerB(cId);
	}
	
	@Transactional
	public int qnaAnswer(int qnaNo, String qnaAnswerComment, String adminId) {
		return adminDao.qnaAnswer(qnaNo,qnaAnswerComment,adminId);
		
	}

	public List selectQnaC(String qNo) {
		return adminDao.selectQnaC(qNo);
	}

	public List question(String input) {
		return adminDao.question(input);
	}
	@Transactional
	public int noticeDel(int noticeNo) {
		return adminDao.noticeDel(noticeNo);
	}
	@Transactional
	public int qnaAnswerDel(int qnaCommentNo) {
		return adminDao.qnaAnswerDel(qnaCommentNo);
	}
	@Transactional
	public int modifyGo(int pw, String phone, String adminId) {
		return adminDao.modifyGo(pw,phone,adminId);
	}

	
}
