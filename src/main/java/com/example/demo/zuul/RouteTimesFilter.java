package com.example.demo.zuul;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UrlPathHelper;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;

public class RouteTimesFilter extends AbstractRouteFilter {
	 //每秒产生1000个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    public RouteTimesFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        super(routeLocator,urlPathHelper);
    }

    @Override
    public String filterType() {
		//可以根据业务要求，修改过滤器类型
        return "post";
    }

    @Override
    public int filterOrder() {
		//过滤器顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
    	RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
    	//只对订单接口限流,返回true ,才会run()
        if ("/apigateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response=ctx.getResponse();
        response.setHeader("X-Foo", UUID.randomUUID().toString());
        HttpServletRequest request = ctx.getRequest();
        Route route = route(ctx.getRequest());
		//获取到路由信息，就可以做想要做的事了
        System.out.println(route);
        
        
         //就相当于每调用一次tryAcquire()方法，令牌数量减1，当1000个用完后，那么后面进来的用户无法访问上面接口
        //当然这里只写类上面一个接口，可以这么写，实际可以在这里要加一层接口判断。
      //判断能否在1秒内得到令牌，如果不能则立即返回false，不会阻塞程序  
        if (!RATE_LIMITER.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
        	 System.out.println("短期无法获取令牌，真不幸");  
        	ctx.setSendZuulResponse(false);
            //HttpStatus.TOO_MANY_REQUESTS.value()里面有静态代码常量
        	ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        System.out.println(RATE_LIMITER.getRate());
        return null;
    }
}