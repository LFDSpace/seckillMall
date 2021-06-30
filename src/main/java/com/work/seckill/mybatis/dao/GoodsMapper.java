package com.work.seckill.mybatis.dao;

import com.work.seckill.entity.SeckillGoods;
import com.work.seckill.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface GoodsMapper {
    @Select("select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.seckill_price, sg.version from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
    public List<GoodsVO> listGoodsVo();

    @Select("select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.seckill_price, sg.version  from sk_goods_seckill sg left join sk_goods g  on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVO getGoodsVoByGoodsId(@Param("goodsId") long goodsId);


    /**
     * stock_count > 0 和 版本号实现乐观锁 防止超卖
     * @param seckillGoods
     * @return
     */
    @Update("update sk_goods_seckill set stock_count = stock_count - 1, version= version + 1 where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
    public int reduceStockByVersion(SeckillGoods seckillGoods);
    @Select("SELECT stock_count FROM sk_goods_seckill WHERE goods_id = #{goodsId}")
    public long selectSeckillGoodsStockCountByGoodsId(long goodsId);

    /**
     * 获取商品最新版本号，用于乐观读
     * @param goodsId
     * @return
     */
    @Select("select version from sk_goods_seckill  where goods_id = #{goodsId}")
    public int getVersionByGoodsId(@Param("goodsId") long goodsId);
}
