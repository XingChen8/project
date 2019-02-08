package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



@Service
public class RedisService {
	
	@Autowired
	JedisPool jedispool;
	
	@Autowired
	RedisConfig redisConfig;

	public <T> T  get(String key,Class<T> clazz) {
		Jedis jedis=null;
		try {
			jedis = jedispool.getResource();
			String str = jedis.get(key);
			T t = stringToBean(str,clazz);
			return t;
		}finally {
			returnToPool(jedis);
		}	
		
	}
	
	public <T> Boolean  set(String key,T value) {
		Jedis jedis=null;
		try {
			jedis = jedispool.getResource();
			String str = beanToString(value);
			if(str == null || str.length()<=0) {
				return false;
			}
			jedis.set(key, str);
			return true;
		}finally {
			returnToPool(jedis);
		}	
		
	}
	
	

	private <T> String beanToString(T value) {
		if(value==null) {
			return null;
		}
		
		Class<?> clazz=value.getClass();
		if(clazz == int.class||clazz == Integer.class) {
			return ""+ value;
		}else if(clazz == String.class) {
			return (String)value;
		}else if(clazz == long.class||clazz == Long.class) {
			return ""+ value;
		}else {
		return JSON.toJSONString(value);
		}
	}
	

	private <T> T stringToBean(String str,Class<T> clazz) {
		
		if(str==null||str.length()<=0||clazz==null) {
			return null;
		}
		if(clazz == int.class||clazz == Integer.class) {
			return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			return (T)str;
		}else if(clazz == long.class||clazz == Long.class) {
			return (T)Long.valueOf(str);
		}else {
		return JSON.toJavaObject(JSON.parseObject(str),clazz);
		}
		
	}
	
	private void returnToPool(Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}
	}
	@Bean
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
		JedisPool jp = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout()*1000,redisConfig.getPassword(),0);		
		return jp;
	}
}
