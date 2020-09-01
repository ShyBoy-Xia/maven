package com.neu.shop.service.impl;

import com.neu.shop.dao.FavoriteMapper;
import com.neu.shop.dao.GoodsMapper;
import com.neu.shop.dao.ImagePathMapper;
import com.neu.shop.pojo.*;
import com.neu.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired(required = false)
	GoodsMapper goodsMapper;
	
	@Autowired(required = false)
    ImagePathMapper imagePathMapper;

    @Autowired(required = false)
    FavoriteMapper favoriteMapper;

	@Override
	public Integer addGoods(Goods goods) {
		goodsMapper.insertSelective(goods);
        return goodsMapper.selectMaxId();
	}

	@Override
	public List<Goods> selectAll() {
		return goodsMapper.selectAllGoods();
	}

	@Override
	public void updateGoodsById(Goods goods) {
		goodsMapper.updateByPrimaryKeySelective(goods);

	}

	@Override
	public Goods selectById(Integer goodsid) {
		return goodsMapper.selectByPrimaryKey(goodsid);
	}

	@Override
	public List<Goods> selectByKeyword(String keyword) {
		return goodsMapper.selectByKeyword(keyword);
	}

	@Override
	public List<Goods> selectBySort(String keyword, int sort) {
		List<Goods> gl = null;
		switch (sort) {
		case 1:
			gl = goodsMapper.selectByKeyword(keyword);
			break;
		case 2:
			break;
		case 3:
			gl = goodsMapper.selectByVolume(keyword);
			break;
		case 4:
			gl = goodsMapper.selectByPriceAsc(keyword);
			break;
		case 5:
			gl = goodsMapper.selectByPriceDesc(keyword);
			break;
		case 6:
			gl = goodsMapper.selectByScore(keyword);
			break;
			
		}
		return gl;
	}

	@Override
	public void addImagePath(ImagePath imagePath) {
		imagePathMapper.insertSelective(imagePath);
		
	}

	@Override
	public void deleteGoodsById(Integer goodsid) {
		goodsMapper.deleteByPrimaryKey(goodsid);
		
	}

	@Override
	public List<ImagePath> findImagePath(Integer goodsid) {
		ImagePathExample imagePathExample = new ImagePathExample();
        imagePathExample.or().andGoodidEqualTo(goodsid);

        return imagePathMapper.selectByExample(imagePathExample);
	}

	@Override
	public void addFavorite(Favorite favorite) {
		favoriteMapper.insertSelective(favorite);	
	}

	@Override
	public Favorite selectFavByKey(FavoriteKey favoriteKey) {
		return favoriteMapper.selectByPrimaryKey(favoriteKey);
	}

	@Override
	public void deleteFavByKey(FavoriteKey favoriteKey) {
		favoriteMapper.deleteByPrimaryKey(favoriteKey);
		
	}

	@Override
	public List<Favorite> selectFavByExample(FavoriteExample favoriteExample) {
		return favoriteMapper.selectByExample(favoriteExample);
	}

	@Override
	public List<Goods> selectByCategory(int cateid, int flag) {
		if(flag == 1) {
			return goodsMapper.selectByFirstCategory(cateid);
		}else{
			return goodsMapper.selectBySecondCategory(cateid);
		}
	}

	@Override
	public List<Goods> selectByCateAndSort(int cate,int cateId, int sort) {
		return null;
	}

	@Override
	public List<Goods> selectByRecommend(int cateid) {
		return goodsMapper.selectByRecommend(cateid);
	}

}
