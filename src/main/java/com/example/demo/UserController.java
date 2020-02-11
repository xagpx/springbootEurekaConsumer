package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.HelloServiceFeign;
import com.example.demo.feign.HelloServiceFeign1;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userservice;
//    @Autowired
//    RefreshRouteService refreshRouteService;
    @Autowired
    private HelloServiceFeign helloServiceFeign;
    @Autowired
    private HelloServiceFeign1 helloServiceFeign1;
    
    @RequestMapping("/getAllUsers")
    public List<String> getUsers(){
        return userservice.getUsers();
    }
     
     @RequestMapping("/hello")
     public String getString(String name){
        return userservice.getString(name);
    }
    
    
    @RequestMapping(value="/hi")
    public String getHi(String name)
    {
        return this.helloServiceFeign.hi(name);
         
    }
    
    @RequestMapping(value="/his")
    public String getHis(String name)
    {
        return this.helloServiceFeign1.his(name);
         
    }
}