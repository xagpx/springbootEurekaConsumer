package com.example.demo.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

/**
 * 此类表示FallBack执行的时候，打印相应的日志
 * 如果需要访问产生回退触发器的原因，可以使用@ feignclient中的fallbackFactory属性。
 *
 */
@Component
public class FeignFallBackFactory implements FallbackFactory<HelloServiceFeign> {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignFallBackFactory.class);
 
    @Override
    public HelloServiceFeign create(Throwable arg0) {
        return new HelloServiceFeign() {
            @Override
            public String hi(String name) {
                return "提供者服务出错";
            }
        };
    }
}