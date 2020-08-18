package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityBrandGroupActivityCustomMapper;
import com.jf.dao.ActivityBrandGroupActivityMapper;
import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupActivityCustom;
import com.jf.entity.ActivityBrandGroupActivityCustomExample;
import com.jf.entity.ActivityBrandGroupActivityExample;

@Service
@Transactional
public class ActivityBrandGroupActivityService extends BaseService<ActivityBrandGroupActivity,ActivityBrandGroupActivityExample > {

	@Autowired
	private ActivityBrandGroupActivityMapper activitybrandgroupactivitymapper;
	
	@Autowired
	private ActivityBrandGroupActivityCustomMapper activitybrandgroupactivitycustommapper;

	@Autowired
	public void setActivityBrandGroupActivityMapper(ActivityBrandGroupActivityMapper activitybrandgroupactivitymapper) {
		super.setDao(activitybrandgroupactivitymapper);
		this.activitybrandgroupactivitymapper = activitybrandgroupactivitymapper;
	}
	
	public List<ActivityBrandGroupActivityCustom> selectByCustomExample(ActivityBrandGroupActivityCustomExample example){
		return activitybrandgroupactivitycustommapper.selectByCustomExample(example);
	}
	
	public Integer countByCustomExample(ActivityBrandGroupActivityCustomExample example){
		return activitybrandgroupactivitycustommapper.countByCustomExample(example);
	}

}
