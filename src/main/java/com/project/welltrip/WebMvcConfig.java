package com.project.welltrip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterceptor")
    private HandlerInterceptor interceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/shop/index.do").setViewName("index");
        registry.addViewController("/login").setViewName("user/login");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor)
//                .addPathPatterns("/shop/editAccount.do", "/shop/listOrders.do",
//                        "/shop/viewOrder.do", "/shop/newOrder.do");
//    }

}