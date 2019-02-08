package com.imooc.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;

@Controller
public class DemoController {
	

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	
	@RequestMapping("/demo")
	@ResponseBody
	String home() {
		return "hello world";
		
	}
	
	
	//1、rest api   2、静态页面
	@RequestMapping("/helloSuccess")
	@ResponseBody
	public Result<String> hello() {
		//static方法可以用类名直接访问在static内部的变量和方法，
		return Result.success("hello.imooc");
//		return new Result(0,"success","hello.imooc");
	}
	
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
//		return new Result(500100,"失效");
	}
	
	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name","yingyan");
		return "hello";
//		return new Result(500100,"失效");
	}
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet() {
		User user = userService.getById(1);
		return Result.success(user);
//		return new Result(500100,"失效");
	}
	
	@RequestMapping("/db/tx")
	@ResponseBody
	public Result<Boolean> dbTx(){
		userService.tx();
		return Result.success(true);
	}
	
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<User> redisGet(){
		User user=redisService.get(UserKey.getById,""+1,User.class);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> redisSet(){
		User user = new User();
		user.setId(1);
		user.setName("111111");
		redisService.set(UserKey.getById,""+1,user);
		return Result.success(true);
	}
	
	
}
