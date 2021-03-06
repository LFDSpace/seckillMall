package com.work.seckill.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Getter
@Setter
// 注解成组件
@Component
//读到配置文件中去
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;//秒
//    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait; //秒
}
