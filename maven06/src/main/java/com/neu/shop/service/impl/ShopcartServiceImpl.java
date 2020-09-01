package com.neu.shop.service.impl;

import com.neu.shop.dao.ShopcartMapper;
import com.neu.shop.pojo.Shopcart;
import com.neu.shop.pojo.ShopcartKey;
import com.neu.shop.service.ShopcartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addShopcart")
public class ShopcartServiceImpl implements ShopcartService {

    @Autowired(required = false)
    ShopcartMapper ShopcartMapper;


    @Override
    public List<Shopcart> selectCartByUser(int userId){
    	return ShopcartMapper.selectByUser(userId);
    }

    @Override
    public void deleteByKey(ShopcartKey ShopcartKey) {
        ShopcartMapper.deleteByPrimaryKey(ShopcartKey);
    }

    @Override
    public void updateCartByKey(Shopcart Shopcart) {
        ShopcartMapper.updateByPrimaryKeySelective(Shopcart);
    }

    @Override
    public Shopcart selectCartByKey(ShopcartKey ShopcartKey) {
        return ShopcartMapper.selectByPrimaryKey(ShopcartKey);
    }

	@Override
	public void addShopCart(Shopcart shopCart) {
		ShopcartMapper.insertSelective(shopCart);
		
	}

	@Override
	public void addOne(ShopcartKey shopCartKey) {
		ShopcartMapper.updateByAddOne(shopCartKey);
		
		
	}

	@Override
	public void deduceOne(ShopcartKey shopCartKey) {
		ShopcartMapper.updateByDeduceOne(shopCartKey);
		
	}

}
