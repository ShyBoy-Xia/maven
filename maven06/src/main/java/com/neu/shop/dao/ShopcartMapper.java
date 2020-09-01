package com.neu.shop.dao;

import java.util.List;

import com.neu.shop.pojo.Shopcart;
import com.neu.shop.pojo.ShopcartKey;

public interface ShopcartMapper {
    int deleteByPrimaryKey(ShopcartKey key);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectByPrimaryKey(ShopcartKey key);

    int updateByPrimaryKeySelective(Shopcart record);

    int updateByPrimaryKey(Shopcart record);
    
    int updateByAddOne(ShopcartKey key);
    
    int updateByDeduceOne(ShopcartKey key);
    
    List<Shopcart> selectByUser(int value);
    
}