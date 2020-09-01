package com.neu.shop.pojo;

import java.util.Date;

public class Shopcart extends ShopcartKey {
    private Date catedate;

    private Integer goodsnum;

    private Double goodssum;

    public Date getCatedate() {
        return catedate;
    }

    public void setCatedate(Date catedate) {
        this.catedate = catedate;
    }

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }

    public Double getGoodssum() {
        return goodssum;
    }

    public void setGoodssum(Double goodssum) {
        this.goodssum = goodssum;
    }
}