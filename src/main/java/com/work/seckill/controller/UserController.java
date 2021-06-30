package com.work.seckill.controller;

import com.work.seckill.base.result.Result;
import com.work.seckill.entity.User;
import com.work.seckill.redis.RedisService;
import com.work.seckill.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RedisService redisService;

    @GetMapping("/info")
    public Result<User> info(User user){
        return Result.success(user);
    }
}
