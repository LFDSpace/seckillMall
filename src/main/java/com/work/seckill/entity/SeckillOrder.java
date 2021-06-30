package com.work.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
