package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityBrandGroupActivityCustomMapper;
import com.jf.dao.ActivityBrandGroupActivityMapper;
import com.jf.dao.ActivityBrandGroupCustomMapper;
import com.jf.dao.ActivityBrandGroupMapper;
import com.jf.entity.ActivityBrandGroup;
import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupCustom;
import com.jf.entity.ActivityBrandGroupCustomExample;
import com.jf.entity.ActivityBrandGroupExample;

@Service
@Transactional
public class ActivityBrandGroupService extends BaseService<ActivityBrandGroup, ActivityBrandGroupExample> {

	@Autowired
	private ActivityBrandGroupMapper activitybrandgroupmapper;
	
	@Autowired
	private ActivityBrandGroupCustomMapper activitybrandgroupcustommapper;
	
	@Autowired
	private ActivityBrandGroupActivityMapper activitybrandgroupactivitymapper;
	
	@Autowired
	private ActivityBrandGroupActivityCustomMapper activitybrandgroupactivitycustommapper;
	
	@Autowired
	public void setActivityBrandGroupMapper(ActivityBrandGroupMapper activitybrandgroupmapper) {
		super.setDao(activitybrandgroupmapper);
		this.activitybrandgroupmapper = activitybrandgroupmapper;
	}
	
	public List<ActivityBrandGroupCustom> selectByCustomExample(ActivityBrandGroupCustomExample example){
		return activitybrandgroupcustommapper.selectByCustomExample(example);
	}
	
	public Integer countByCustomExample(ActivityBrandGroupCustomExample example){
		return activitybrandgroupcustommapper.countByCustomExample(example);
	}
	//添加至特卖品牌团-特卖关联表列表
	public void addActivityBrandGroupactivity(Integer staffID, String activityareaid, String activitybrandgroupid) {
		String[] strs = activityareaid.split(",");
		Date date = new Date();
		for(String activityareaids : strs) {
			ActivityBrandGroupActivity activityBrandGroupActivity = new ActivityBrandGroupActivity();
			activityBrandGroupActivity.setActivityBrandGroupId(Integer.parseInt(activitybrandgroupid));
			activityBrandGroupActivity.setActivityAreaId(Integer.parseInt(activityareaids));
			activityBrandGroupActivity.setCreateBy(staffID);
			activityBrandGroupActivity.setCreateDate(date);
			activityBrandGroupActivity.setUpdateDate(date);
			activityBrandGroupActivity.setDelFlag("0");
			activitybrandgroupactivitymapper.insert(activityBrandGroupActivity);
		}
	}

}
