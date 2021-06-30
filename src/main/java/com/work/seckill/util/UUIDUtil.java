package com.work.seckill.util;

import java.util.UUID;


public class UUIDUtil {
    public static String uuid(){
//        生成一个随机的的UUid，然后原生的uuid是有-横杠的，去掉横杠
        return UUID.randomUUID().toString().replace("-","");
    }
}

