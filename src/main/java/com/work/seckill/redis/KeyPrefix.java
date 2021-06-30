package com.work.seckill.redis;

//key的前缀   保证key不被覆盖掉
public interface KeyPrefix {
    /**
     * 有效期
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();

}

