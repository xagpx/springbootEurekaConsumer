package com.example.demo.feign;

import org.springframework.stereotype.Component;
/**
 * 此类表示FallBack执行的时候，打印相应的日志
 * 如果需要访问产生回退触发器的原因，可以使用@ feignclient中的fallbackFactory属性。
 *
 */
@Component
public class FeignFallBackFactory1 implements HelloServiceFeign1 {
 
	public String his(String name) {
		 return "提供者服务出错11";
	}
}