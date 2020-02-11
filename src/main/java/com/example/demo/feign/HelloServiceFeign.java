package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="eureka-provider",fallbackFactory = FeignFallBackFactory.class)
public interface  HelloServiceFeign {
	 //服务中方法的映射路径
    @RequestMapping("/hello")
    String hi(@RequestParam(value = "name") String name);
}
