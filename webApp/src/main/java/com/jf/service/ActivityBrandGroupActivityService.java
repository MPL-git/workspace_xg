package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityBrandGroupActivityMapper;
import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupActivityExample;

@Service
@Transactional
public class ActivityBrandGroupActivityService extends BaseService<ActivityBrandGroupActivity, ActivityBrandGroupActivityExample> {
	@Autowired
	private ActivityBrandGroupActivityMapper activityBrandGroupActivityMapper;
	@Autowired
	public void setActivityBrandGroupActivityMapper(ActivityBrandGroupActivityMapper activityBrandGroupActivityMapper) {
		this.setDao(activityBrandGroupActivityMapper);
		this.activityBrandGroupActivityMapper = activityBrandGroupActivityMapper;
	}
	
	
}
