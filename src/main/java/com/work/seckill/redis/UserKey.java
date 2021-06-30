package com.work.seckill.redis;


public class UserKey extends BasePrefix{
    public static final int TOKEN_EXPIRE = 3600*24*2;
    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");
    public static UserKey getByPhoneNum = new UserKey(0,"phoneNum");

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

}
