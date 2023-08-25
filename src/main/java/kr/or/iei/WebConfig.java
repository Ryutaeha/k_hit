package kr.or.iei;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
	}
	
}
