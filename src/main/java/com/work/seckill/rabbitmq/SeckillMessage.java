package com.work.seckill.rabbitmq;

import com.work.seckill.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeckillMessage {
    private User user;
    private long goodsId;
}

