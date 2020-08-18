package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityGroupMapper;
import com.jf.entity.ActivityGroup;
import com.jf.entity.ActivityGroupExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月3日 下午3:34:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ActivityGroupService extends BaseService<ActivityGroup, ActivityGroupExample> {
	@Autowired
	private ActivityGroupMapper activityGroupMapper;

	@Autowired
	public void setActivityGroupMapper(ActivityGroupMapper activityGroupMapper) {
		this.setDao(activityGroupMapper);
		this.activityGroupMapper = activityGroupMapper;
	}

	public ActivityGroup getActivityGroupModelByGroupId(Integer activityGroupId) {
		ActivityGroup activityGroup = null;
		ActivityGroupExample groupExample = new ActivityGroupExample();
		groupExample.createCriteria().andIdEqualTo(activityGroupId).andDelFlagEqualTo("0");
		List<ActivityGroup> activityGroups = selectByExample(groupExample);
		if(CollectionUtils.isNotEmpty(activityGroups)){
			activityGroup = activityGroups.get(0);
		}
		return activityGroup;
	}
	
}
