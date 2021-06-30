package com.work.seckill.redis;


public class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;
//  0代表永不过期
    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
//    过期时间  默认为0时表示永不过期
    @Override
    public int expireSeconds() {
        return this.expireSeconds;
    }

//    保障prefix不重复，使用同一类名就行
    @Override
    public String getPrefix() {
//        获取类名
        String className = getClass().getSimpleName();
        return className+":"+this.prefix;
    }
}
