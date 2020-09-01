package com.neu.shop.service.impl;

import com.neu.shop.dao.CategoryMapper;
import com.neu.shop.dao.CategorySecMapper;
import com.neu.shop.pojo.Category;
import com.neu.shop.pojo.CategorySec;
import com.neu.shop.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cateService")
public class CateServiceImpl implements CateService {

    @Autowired(required = false)
    CategoryMapper categoryMapper;
    
    @Autowired(required = false)
    CategorySecMapper categorySecMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAllCategories();
    }
    
    @Override
    public List<CategorySec> selectAllCate2() {
        return categorySecMapper.selectAll();
    }

    @Override
    public void insertSelective(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public Category selectById(Integer category) {
        return categoryMapper.selectByPrimaryKey(category);
    }
    
    @Override
    public CategorySec selectSecById(Integer category) {
        return categorySecMapper.selectByPrimaryKey(category);
    }

    @Override
    public void updateByPrimaryKeySelective(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void deleteByPrimaryKey(Integer cateid) {
        categoryMapper.deleteByPrimaryKey(cateid);
    }

	@Override
	public List<CategorySec> selectAllSecCategoryByFirst(Integer cateId) {
		return categorySecMapper.selectByFirstCategory(cateId);
	}

	@Override
	public List<Category> selectRepeat(String value) {
		return categoryMapper.selectRepeat(value);
	}

	@Override
	public List<CategorySec> selectRepeatSec(String value) {
		return categorySecMapper.selectRepeat(value);
	}

	@Override
	public void insertSelective(CategorySec categorysec) {
		categorySecMapper.insertSelective(categorysec);	
	}

	@Override
	public void updateByPrimaryKeySelective(CategorySec categorysec) {
		categorySecMapper.updateByPrimaryKeySelective(categorysec);
		
	}

	@Override
	public void deleteCateSecByPrimaryKey(Integer catesecid) {
		categorySecMapper.deleteByPrimaryKey(catesecid);
		
	}
}
