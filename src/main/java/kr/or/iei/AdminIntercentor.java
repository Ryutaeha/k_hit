package kr.or.iei;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.or.iei.admin.model.vo.Admin;


public class AdminIntercentor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("dd");
		HttpSession session = request.getSession();
		Admin a = (Admin)session.getAttribute("a");
		if(a == null) {
			response.sendRedirect("/admin/adminMsg");
			return false;
		}else {			
			if(a.getMemberCode()==1) {
				return true;
			}else {
				response.sendRedirect("/admin/adminMsg");
				return false;
			}
		}
	}
}
