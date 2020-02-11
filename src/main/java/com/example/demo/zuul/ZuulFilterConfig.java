package com.example.demo.zuul;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.netflix.zuul.ZuulFilter;

@Component
public class ZuulFilterConfig {

    @Bean
    public ZuulFilter routeTimesFilter(RouteLocator routeLocator){
        return new RouteTimesFilter(routeLocator,new UrlPathHelper());
    }

}