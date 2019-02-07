package com.imooc.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;

@Controller
public class DemoController {
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
	
}
