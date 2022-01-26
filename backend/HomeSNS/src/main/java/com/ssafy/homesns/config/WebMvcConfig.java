//package com.ssafy.homesns.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.ssafy.homesns.common.LoginInterceptor;
//
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    // LoginInterceptor를 사용하여, 필터 역할을 수행한다
//    // index, login, register를 제외한 곳은 로그인 상태가 아니면 갈 수 없다
//    // Vue가 생성해주는 파일 구성에 맞춰서 Interceptor를 설정해준다
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//        .addPathPatterns("/**")
//        .excludePathPatterns("/login.html")
//        .excludePathPatterns("/login/**", "/register/**", "/codes/**") // html 사용할 경우
//        .excludePathPatterns("/css/**", "/fonts/**", "/img/**", "/js/**", "/include/**")
//        .excludePathPatterns(
//        		"/", 
//        		"/index.html",
//        		"favicom.ico",
//        		"/css/**",
//        		"/js/**", 
//        		"/login/**",
//        		"/codes/**",
//        		"/register/**",
//        		"/kakaoLogin/**",
//        		"/kakaoToken/**",
//        		"/kakaoTokenInfo/**",
//        		"/kakaoLogout/**",
//        		"/mypage/**"
//        		);
//       
//    }
//
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/").setViewName("/static/login.html");
////        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
////    }
//}