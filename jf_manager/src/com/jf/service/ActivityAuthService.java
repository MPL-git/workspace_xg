package com.jf.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityAuthCustomMapper;
import com.jf.dao.ActivityAuthMapper;
import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityAuthExample;

@Service
@Transactional
public class ActivityAuthService extends BaseService<ActivityAuth, ActivityAuthExample>{
	@Autowired
	private ActivityAuthMapper activityAuthMapper;
	@Resource
	private ActivityAuthCustomMapper activityAuthCustomMapper;
	
	@Autowired
	public void setActivityAuthMapper(ActivityAuthMapper activityAuthMapper) {
		super.setDao(activityAuthMapper);
		this.activityAuthMapper = activityAuthMapper;
	}

	public ActivityAuth selectByActivityAuthCustomExample(Integer activityId){
		return activityAuthCustomMapper.selectByActivityAuthCustomExample(activityId);
	}
	
	public Integer selectByActivityAuthSelect(Integer activityId){
		return activityAuthCustomMapper.selectByActivityAuthSelect(activityId);
	}
	
	public Integer selectByActivityAuthProductAll(Integer activityId){
		return activityAuthCustomMapper.selectByActivityAuthProductAll(activityId);
	}
}
