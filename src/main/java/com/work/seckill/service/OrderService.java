package com.work.seckill.service;

import com.work.seckill.mybatis.dao.OrderMapper;
import com.work.seckill.entity.OrderInfo;
import com.work.seckill.entity.SeckillOrder;
import com.work.seckill.entity.User;
import com.work.seckill.redis.OrderKey;
import com.work.seckill.redis.RedisService;
import com.work.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    RedisService redisService;

    public SeckillOrder getOrderByUserIdAndGoodsId(long userId,long goodsId){
        return redisService.get(OrderKey.getSeckillOrderByUidAndGid(),""+userId+"_"+goodsId,SeckillOrder.class);
    }
    public OrderInfo getOrderById(long orderId){
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 订单详情表和秒杀订单表同时增加一条数据
     * @param user
     * @param goodsVO
     * @return
     */
    @Transactional
    public OrderInfo createOrder(User user, GoodsVO goodsVO){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderMapper.add(orderInfo);

        long orderId = orderMapper.selectOrderInfoIdByUserIdAndGoodsId(orderInfo.getUserId(),orderInfo.getGoodsId());

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        orderMapper.addSeckillOrder(seckillOrder);

        redisService.set(OrderKey.getSeckillOrderByUidAndGid(),""+user.getId()+"_"+ goodsVO.getId(),seckillOrder);
        return orderInfo;
    }
}
