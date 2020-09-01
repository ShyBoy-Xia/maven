package com.neu.shop.service;


import java.util.List;

import com.neu.shop.pojo.Shopcart;
import com.neu.shop.pojo.ShopcartKey;

public interface ShopcartService {
    public void addShopCart(Shopcart shopCart);

    public void deleteByKey(ShopcartKey shopcartKey);

    public void updateCartByKey(Shopcart shopcart);

    public Shopcart selectCartByKey(ShopcartKey shopCartKey);
    
    public List<Shopcart> selectCartByUser(int userId);
    
    public void addOne(ShopcartKey shopCartKey);
    
    public void deduceOne(ShopcartKey shopCartKey);
}
