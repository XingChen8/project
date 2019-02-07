package com.imooc.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;
	//通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
	//订单模块
	
	//商品模块
	
	public CodeMsg(int code, String msg) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
