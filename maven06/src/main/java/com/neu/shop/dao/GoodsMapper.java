package com.neu.shop.dao;

import java.util.List;


import com.neu.shop.pojo.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    
    List<Goods> selectAllGoods();
    
    List<Goods> selectByKeyword(String value);
    
    List<Goods> selectByVolume(String value);
    
    List<Goods> selectByPriceAsc(String value);
    
    List<Goods> selectByPriceDesc(String value);
    
    List<Goods> selectByScore(String value);
    
    List<Goods> selectByFirstCategory(Integer cateid);
    
    List<Goods> selectBySecondCategory(Integer cateid);
    
    List<Goods> selectByRecommend(Integer cateid);
    
    int selectMaxId();
    
}