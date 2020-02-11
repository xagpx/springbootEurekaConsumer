package com.example.demo;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {

	/*@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
		return new ServletRegistrationBean<HystrixMetricsStreamServlet>(new HystrixMetricsStreamServlet(),
				"/hystrix.stream");
	}*/
	
	@Bean
   public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
	/**
	 * AspectJ aspect to process methods which annotated with
	 * {@link HystrixCommand} annotation.
	 *
	 * {@link HystrixCommand} annotation used to specify some methods which
	 * should be processes as hystrix commands.
	 */
	@Bean
	public HystrixCommandAspect hystrixCommandAspect() {
		return new HystrixCommandAspect();
	}
}