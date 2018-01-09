package com.github.wxz.condition;


import com.github.wxz.enums.SortMode;
import com.github.wxz.utils.Pagination;

/**
 * @author xianzhi.wang
 * @date 2017/1/9 -17:11
 */
public class GoodsSearchCondition {
    /**
     * 商品名称模糊
     */

    private String goodsName;
    /**
     * 分类名称模糊
     */
    private String cateGoryName;

    /**
     * 规格模糊
     */
    private String skuAttr;
    /**
     * 排序字段
     */
    private SortMode sortMode;
    /**
     * 开始条数
     */
    private Integer offset = (Pagination.DEFAULT_PAGE_NUM - 1) * Pagination.DEFAULT_PAGE_SIZE;
    /**
     * 多少条
     */
    private Integer pageSize = Pagination.DEFAULT_PAGE_SIZE;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SortMode getSortMode() {
        return sortMode;
    }

    public void setSortMode(SortMode sortMode) {
        this.sortMode = sortMode;
    }

    public String getCateGoryName() {
        return cateGoryName;
    }

    public void setCateGoryName(String cateGoryName) {
        this.cateGoryName = cateGoryName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

}
