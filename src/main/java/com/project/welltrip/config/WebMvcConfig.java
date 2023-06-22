package com.project.welltrip.config;

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
        // home 으로 이동
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("user/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/follow/list", "/follow/test");
    }

    /*
     * TODOS :
     *
     * 1. ddl 설정이 create 로 되어 있기 떄문에 DB Contents 가 올라갈 수 없음
     * 		=> Insert 를 어떻게 할것인가. User 입장에서는 여행 상품을 검색하고 결제할 수만 있음
     * 		=> 해결 방안 : PL/SQL 로 100만개 까지 데이터를 집어 넣을 수 있음. / 아니면 수작업으로 하나씩 입력해서 10개? 정도만 입력
     *
     * 2. DB에 데이터가 없기 때문에 여행 상품 내역을 불러올 수 가 없기때문에 페이지에서 List 가 보이지 않는다. (테스트는 JUnit 으로 insert, update를 통해 확인하였음.)
     *
     *
     * 3. 현재 문제가 있는 점. (추가 필요한 작업들)
     * 		- search 페이지에서 사용자가 여행상품을 선택한 검색 버튼을 클릭했을 때,
     * 		  선택된 여행 상품의 데이터를 controller로 넘기지 못하여 checkout 에서 model 을 넘겨주는 작업을 완성하지 못함.
     *
     *   	- airplane Entity 를 어떻게 사용해야 할지 아이디어가 없음.
     *
     *   	- 예약 정보가 등록되면 예약 정보는 불러올 수 있으나 userId 를 reservation Entity 에 필드로 넣지 못해서
     *   		JoinColumn 을 사용하야 하는데 사용하지 못하였음.
     *
     *
     */


}