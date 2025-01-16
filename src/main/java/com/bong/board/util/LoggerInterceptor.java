package com.bong.board.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       
		log.debug("==================== BEGIN ====================");
        log.debug("Request URI \t: " + request.getRequestURI());
        
        // "/board"로 시작하는 경로에 대해서만 로그인 여부를 확인
        if (request.getRequestURI().startsWith("/board")) {
           
            if (request.getSession().getAttribute("memberId") == null) {
                log.debug("NOT SIGNIN");
                request.getSession().setAttribute("loginMessage", "로그인 후 이용할 수 있습니다.");
                response.sendRedirect("/signin"); // 로그인 페이지로 리디렉션
                return false;  
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("==================== END ======================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
}
