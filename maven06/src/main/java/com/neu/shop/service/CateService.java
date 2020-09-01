package com.neu.shop.service;

import com.neu.shop.pojo.Category;
import com.neu.shop.pojo.CategorySec;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("CateService")
public interface CateService {
    public List<Category> selectAll();
    
    public List<CategorySec> selectAllSecCategoryByFirst(Integer cateId);
    
    public void insertSelective(Category category);

    public Category selectById(Integer category);

    public void updateByPrimaryKeySelective(Category category);

    public void deleteByPrimaryKey(Integer cateid);
    
    public CategorySec selectSecById(Integer category);
    
    public List<CategorySec> selectAllCate2();
    
    public List<Category> selectRepeat(String value);
    
    public List<CategorySec> selectRepeatSec(String value);
    
    public void insertSelective(CategorySec categorysec);


    public void updateByPrimaryKeySelective(CategorySec categorysec);

    public void deleteCateSecByPrimaryKey(Integer catesecid);
}
