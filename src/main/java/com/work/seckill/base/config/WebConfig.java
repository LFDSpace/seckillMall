package com.work.seckill.base.config;

import com.work.seckill.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    UserArgumentResolver userArgumentResolver;

    @Resource
    AccessInterceptor accessInterceptor;

    /**
     * SpringMVC框架回调addArgumentResolvers，然后给Controller的参数赋值
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
    }
}
