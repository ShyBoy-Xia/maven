package com.neu.shop.pojo;

public class ShopcartKey {
    private Integer userid;

    private Integer goodsid;
    
    public ShopcartKey() {
	}

    public ShopcartKey(Integer userid, Integer goodsid) {
		this.userid = userid;
		this.goodsid = goodsid;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
}