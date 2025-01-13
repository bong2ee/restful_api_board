package com.bong.board.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/*
	 * 리소스핸들러 
	 * 프로젝트 외부에 저장된 파일을 호출하기 위해 따로 설정함
	 * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/summernoteImg/**") //  "/summernoteImg/"로 시작하는 모든 요청에 대해 이 핸들러가 작동
        		.addResourceLocations("file:///C:/summernoteImg/"); // 실제 파일 시스템에서 해당 정적 리소스를 찾을 경로를 설정
    }
    
}