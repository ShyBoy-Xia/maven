package com.neu.shop.dao;

import java.util.List;

import com.neu.shop.pojo.CategorySec;

public interface CategorySecMapper {
    int deleteByPrimaryKey(Integer catesecid);

    int insert(CategorySec record);

    int insertSelective(CategorySec record);

    CategorySec selectByPrimaryKey(Integer catesecid);

    int updateByPrimaryKeySelective(CategorySec record);

    int updateByPrimaryKey(CategorySec record);
    
    List<CategorySec> selectByFirstCategory(Integer value);
    
    List<CategorySec> selectAll();
    
    List<CategorySec> selectRepeat(String value);
}