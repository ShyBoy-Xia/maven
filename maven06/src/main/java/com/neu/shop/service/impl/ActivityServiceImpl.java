package com.neu.shop.service.impl;

import com.neu.shop.dao.ActivityMapper;
import com.neu.shop.pojo.Activity;
import com.neu.shop.pojo.ActivityExample;
import com.neu.shop.pojo.Goods;
import com.neu.shop.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired(required = false)
    ActivityMapper activityMapper;

    @Override
    public void insertActivitySelective(Activity activity) {
        activityMapper.insertSelective(activity);
    }

    @Override
    public Activity selectByKey(Integer activityid) {
        return activityMapper.selectByPrimaryKey(activityid);
    }

    @Override
    public void deleteByActivityId(Integer activityid) {
        activityMapper.deleteByPrimaryKey(activityid);
    }

	@Override
	public List<Activity> getAllActivity(ActivityExample activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGoodsActSelective(Goods goods) {
		// TODO Auto-generated method stub
		
	}
}
