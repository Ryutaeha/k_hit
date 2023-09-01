package kr.or.iei;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.or.iei.customer.model.vo.Customer;


public class CustomerInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("c");
		if(c == null) {
			response.sendRedirect("/customer/customerMsg");
			return false;
		}else {
			return true;
		}
	}
}
