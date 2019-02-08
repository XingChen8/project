package com.imooc.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {

	private int expireSeconds;
	
	private String prefix;
	
	//abstract的类无法直接new构造方法
	public BasePrefix(String prefix) {
		this(0, prefix);
		
	}
	
	public BasePrefix(int expireSeconds,String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	
	@Override
	public int expireSeconds() {
		//默认0代表不过期
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		String className=getClass().getSimpleName();
		return className+":"+prefix;
	}

}
