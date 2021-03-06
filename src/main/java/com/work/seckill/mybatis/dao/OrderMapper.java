package com.work.seckill.mybatis.dao;

import com.work.seckill.entity.OrderInfo;
import com.work.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.*;


@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM sk_order WHERE user_id#{userId} AND goods_id = #{goodsId}")
    SeckillOrder selectByUserIdAndGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into sk_order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    public int add(OrderInfo orderInfo);

    @Select("SELECT id FROM sk_order_info WHERE user_id=#{userId} AND goods_id=#{goodsId}")
    public long selectOrderInfoIdByUserIdAndGoodsId(long userId,long goodsId);

    @Insert("INSERT INTO sk_order(user_id,goods_id,order_id)values(#{userId},#{goodsId},#{orderId})")
    public int addSeckillOrder(SeckillOrder seckillOrder);

    @Select("SELECT * FROM sk_order_info WHERE id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId") long orderId);
}
