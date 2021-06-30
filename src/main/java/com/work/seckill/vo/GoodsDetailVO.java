package com.work.seckill.vo;

import com.work.seckill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVO {
    private Integer seckillStatus;
    private Integer remainSeconds;
    private GoodsVO goods ;
    private User user;
}
