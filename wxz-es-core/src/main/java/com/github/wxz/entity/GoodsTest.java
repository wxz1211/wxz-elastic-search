package com.github.wxz.entity;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class GoodsTest implements IdAble {
    private String goodsName;
    private String goodsFixName;
    private Integer id;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFixName() {
        return goodsFixName;
    }

    public void setGoodsFixName(String goodsFixName) {
        this.goodsFixName = goodsFixName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
