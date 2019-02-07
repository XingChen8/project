package com.imooc.miaosha.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	@RequestMapping("/demo")
    @ResponseBody
    public String hello(){
        return "hello world！";
    }
	
	@RequestMapping("/hello")
    @ResponseBody
    public String helloSuccess(){
        return "hello world！";
    }
	
	@RequestMapping("/error")
    @ResponseBody
    public String helloError(){
        return "hello world！";
    }
	
	
	
	
}
