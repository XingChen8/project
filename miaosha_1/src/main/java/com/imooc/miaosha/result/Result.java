package com.imooc.miaosha.result;
//泛型类
public class Result<T> {
	private int code;
	private String msg;
	//泛型变量
	private T data;
	//泛型参数
	
	//构造函数重载，private只能在其自己定义的类中使用。成功时初始化数据。
	private Result(T data) {
		// TODO Auto-generated constructor stub
		this.code=0;
		this.msg="success";
		this.data = data;
		
	}
	//构造函数重载，错误时初始化数据
	private Result(CodeMsg cm) {
		// TODO Auto-generated constructor stub
		if(cm == null) {
			return;
		}
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}

	/**
	 * 成功时调用
	 * @return
	 */
	public static <T>  Result<T> success(T data){
		return new Result<T>(data);
	}
	
	/**
	 * 失败时调用,传Codemsg对象。
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm){
		return new Result<T>(cm);
	}
	
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
	
}
