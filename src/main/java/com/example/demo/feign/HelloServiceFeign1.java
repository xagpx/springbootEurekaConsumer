package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="eureka-provider",fallback= FeignFallBackFactory1.class)
public interface  HelloServiceFeign1 {
	 //服务中方法的映射路径
    @RequestMapping("/hello")
    String his(@RequestParam(value = "name") String name);
}
