package com.cinemoa.config;

import com.cinemoa.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//웹 설정 클래스 (인터셉터 등록, 리소스 핸들러, CORS 설정 등)
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LoginCheckInterceptor를 등록
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/mypage/**") // 이 경로 이하에 모두 적용
                .excludePathPatterns("/member/**", "/mypage/withdrawalSuccess");   // 로그인 관련 경로는 제외 (로그인 페이지로의 무한 리다이렉트 방지), 회원탈퇴 완료 페이지는 방지
    }    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/profile/**")
                .addResourceLocations("file:///C:/cinemoa-profile/");
    }
}
