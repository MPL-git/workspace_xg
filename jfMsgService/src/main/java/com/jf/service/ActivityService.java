package com.jf.service;

import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityCustomMapper;
import com.jf.dao.ActivityMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityExample;

@Service
@Transactional
public class ActivityService extends BaseService<Activity, ActivityExample> {
	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private ActivityCustomMapper activityCustomMapper;

	@Autowired
	public void setActivityMapper(ActivityMapper activityMapper) {
		this.setDao(activityMapper);
		this.activityMapper = activityMapper;
	}
	
	public List<Map<String, Object>> getActivityDepositOrderBeginList(Map<String, Object> paramMap) {
		return activityCustomMapper.getActivityDepositOrderBeginList(paramMap);
	}
	
	public List<Map<String, Object>> getActivityDepositOrderCloseList(Map<String, Object> paramMap) {
		return activityCustomMapper.getActivityDepositOrderCloseList(paramMap);
	}

	
}
