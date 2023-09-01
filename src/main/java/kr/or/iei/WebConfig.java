package kr.or.iei;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //객체생성 어노테이션이면서 스프링 설정파일을 의미
@EnableWebMvc //Spring Boot의 기본설정을 내가 수정
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//원래 기본설정 추가(html -> templates, 기본자원들 -> static폴더 사용
		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/","classpath:/static/");
		
		registry.addResourceHandler("/product/**").addResourceLocations("file:///C:/khit/upload/product/");
		
		registry.addResourceHandler("/editor/**").addResourceLocations("file:///C:/khit/upload/editor/");
		
		registry.addResourceHandler("/seller/**").addResourceLocations("file:///C:/khit/upload/seller/");
		
		registry.addResourceHandler("/review/**").addResourceLocations("file:///C:/khit/upload/review/");
		
		registry.addResourceHandler("/admin/**").addResourceLocations("file:///C:/khit/upload/notice/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
	
		registry.addInterceptor(new AdminIntercentor())
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/admin/adminLogin","/admin/login","/admin/adminMsg");
	
		registry.addInterceptor(new SellerInterceptor())
		.addPathPatterns("/seller/**", "/product/deleteProduct", "/product/updateFrm", "/product/update", "/product/changeStockStatus")
		.excludePathPatterns("/seller/cancelRefundBtn","/seller/joinConfirm","/seller/join","/seller/joinComplete","/seller/checkId","/seller/searchConfirm","/seller/searchIdPwFrm","/seller/sellerMsg","/seller/signin","seller/logout");
		
		registry.addInterceptor(new CustomerInterceptor())
		.addPathPatterns("/customer/**","/review/reviewWriteFrm","/review/write","/review/addLike","/review/removeLIke","/review/insertComment","/review/deleteComment","/review/updateComment","/product/deleteProduct","/seller/cancelRefundBtn")
		.excludePathPatterns("/customer/joinConfirm","/customer/join","/customer/joinComplete","/customer/searchIdPwFrm","/customer/customerMsg","/customer/signin","customer/logout");
	}
	
}
