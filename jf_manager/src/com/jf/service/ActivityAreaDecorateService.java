package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityAreaDecorateMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.ActivityAreaDecorate;
import com.jf.entity.ActivityAreaDecorateExample;
import com.jf.entity.DecorateInfo;

@Service
@Transactional
public class ActivityAreaDecorateService extends BaseService<ActivityAreaDecorate,ActivityAreaDecorateExample> {
	@Autowired
	private ActivityAreaDecorateMapper activityAreaDecorateMapper;
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	public void setActivityAreaDecorateMapper(ActivityAreaDecorateMapper activityAreaDecorateMapper) {
		super.setDao(activityAreaDecorateMapper);
		this.activityAreaDecorateMapper = activityAreaDecorateMapper;
	}
	public void save(DecorateInfo decorateInfo,ActivityAreaDecorate activityAreaDecorate) {
		decorateInfoMapper.insertSelective(decorateInfo);
		activityAreaDecorate.setDecorateInfoId(decorateInfo.getId());
		this.insertSelective(activityAreaDecorate);
	}
	
}
