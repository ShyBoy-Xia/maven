package com.neu.shop.service;

import com.neu.shop.pojo.*;

import java.util.List;


public interface GoodsService {
    public Integer addGoods(Goods goods);

    public void addImagePath(ImagePath imagePath);

    public List<Goods> selectAll();
    
    //关键字查找(模糊)
    public List<Goods> selectByKeyword(String keyword);
    
    //关键字排序
    public List<Goods> selectBySort(String keyword,int sort);
    
    //分类排序
    public List<Goods> selectByCateAndSort(int cate,int cateId,int sort);
    
    //分类查找
    public List<Goods> selectByCategory(int cateid,int flag);
    
    public List<Goods> selectByRecommend(int cateid);
   

    public void deleteGoodsById(Integer goodsid);

    public void updateGoodsById(Goods goods);

    public List<ImagePath> findImagePath(Integer goodsid);

    public Goods selectById(Integer goodsid);

   // public List<Goods> selectByExampleLimit(GoodsExample digGoodsExample);

    public void addFavorite(Favorite favorite);

    public Favorite selectFavByKey(FavoriteKey favoriteKey);

    public void deleteFavByKey(FavoriteKey favoriteKey);

    public List<Favorite> selectFavByExample(FavoriteExample favoriteExample);
}
