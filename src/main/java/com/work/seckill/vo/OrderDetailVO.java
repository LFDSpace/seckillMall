package com.work.seckill.vo;

import com.work.seckill.entity.OrderInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailVO {
    private GoodsVO goods;
    private OrderInfo orderInfo;

}
