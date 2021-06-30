package com.work.seckill.vo;

import com.work.seckill.entity.Goods;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodsVO extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Integer version;
}
