package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityAreaMapper;
import com.jf.dao.AppAccessTokenMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityAreaService extends BaseService<ActivityArea, ActivityAreaExample> {
	@Autowired
	private ActivityAreaMapper activityAreaMapper;
	
	@Autowired
	private AppAccessTokenMapper appAccessTokenMapper;
	
	
	@Autowired
	public void setActivityAreaMapper(ActivityAreaMapper activityAreaMapper) {
		super.setDao(activityAreaMapper);
		this.activityAreaMapper = activityAreaMapper;
	}


	public ActivityArea findModel(Integer activityAreaId) {
		
		return activityAreaMapper.selectByPrimaryKey(activityAreaId);
	}
	
}
