package com.neu.shop.dao;

import java.util.List;

import com.neu.shop.pojo.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cateid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cateid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> selectAllCategories();
    
    List<Category> selectRepeat(String value);
}