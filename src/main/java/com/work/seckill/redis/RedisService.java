package com.work.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;


//提供redis服务
@Service
public class RedisService {
    @Resource
    private JedisPool jedisPool;
//  获取单个对象
    public <T> T get(KeyPrefix prefix,String key,Class<T> tClass){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
//            生成真正的key
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,tClass);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

//     设置对象
    public <T> Boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            String realKey = prefix.getPrefix()+key;
            int expire = prefix.expireSeconds();
            if(expire <= 0){
                jedis.set(realKey,str);
            }else{
//                多一个过期时间
                jedis.setex(realKey,expire,str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey =prefix.getPrefix()+key;
            long ret = jedis.del(realKey);
            return ret > 0;
        }finally {
            returnToPool(jedis);
        }
    }
//    判断是否存在
    public<T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
//  增加值
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
//    减少值
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    private void returnToPool(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }
    public static <T> String beanToString(T value){
        if(null == value){
            return null;
        }
        Class<?> c = value.getClass();
        if(c == Integer.class){
            return String.valueOf(value);
        }else if(c == Long.class){
            return String.valueOf(value);
        }else if(c == String.class){
            return (String) value;
        }else{
            return JSON.toJSONString(value);
        }
    }
    public static  <T> T stringToBean(String str, Class<T> tClass) {
        if(null == str || str.length() <= 0 || null == tClass){
            return null;
        }
        if(tClass == int.class || tClass == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(tClass == long.class || tClass == Long.class){
            return (T)Long.valueOf(str);
        }else if(tClass == String.class){
            return (T)str;
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),tClass);
        }
    }


}
