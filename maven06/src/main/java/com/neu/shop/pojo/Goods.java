package com.neu.shop.pojo;

import java.util.Date;
import java.util.List;

public class Goods {
    private Integer goodsid;

    private String goodsname;

    private Integer price;

    private Integer num;

    private Date uptime;

    private Double score;

    private Integer volume;

    private Integer category;

    private Integer category2;

    private String detailcate;

    private String description;

    private Integer activityid;
    
    private List<ImagePath> imagePaths;

    private boolean fav;

    private Activity activity;

    private Float newPrice;
    
    private String cate1;
    
    private String cate2;
    
    private double sales;
    
    

    public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public String getCate1() {
		return cate1;
	}

	public void setCate1(String cate1) {
		this.cate1 = cate1;
	}

	public String getCate2() {
		return cate2;
	}

	public void setCate2(String cate2) {
		this.cate2 = cate2;
	}

	public List<ImagePath> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(List<ImagePath> imagePaths) {
		this.imagePaths = imagePaths;
	}

	public boolean isFav() {
		return fav;
	}

	public void setFav(boolean fav) {
		this.fav = fav;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Float getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Float newPrice) {
		this.newPrice = newPrice;
	}

	public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCategory2() {
        return category2;
    }

    public void setCategory2(Integer category2) {
        this.category2 = category2;
    }

    public String getDetailcate() {
        return detailcate;
    }

    public void setDetailcate(String detailcate) {
        this.detailcate = detailcate == null ? null : detailcate.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

	@Override
	public String toString() {
		return "Goods [goodsid=" + goodsid + ", goodsname=" + goodsname + ", price=" + price + ", num=" + num
				+ ", uptime=" + uptime + ", score=" + score + ", volume=" + volume + ", category=" + category
				+ ", category2=" + category2 + ", detailcate=" + detailcate + ", description=" + description
				+ ", activityid=" + activityid + "]";
	}
    
    
}