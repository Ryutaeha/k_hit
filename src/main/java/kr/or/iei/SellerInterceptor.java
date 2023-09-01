package kr.or.iei;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.or.iei.seller.model.vo.Seller;

public class SellerInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Seller s = (Seller)session.getAttribute("s");
		if(s == null) {
			response.sendRedirect("/seller/sellerMsg");
			return false;
		}else {
			return true;
		}
	}
}
