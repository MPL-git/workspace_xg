package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityGroupCustomMapper;
import com.jf.dao.ActivityGroupMapper;
import com.jf.entity.ActivityGroup;
import com.jf.entity.ActivityGroupCustom;
import com.jf.entity.ActivityGroupExample;

@Service
@Transactional
public class ActivityGroupSerivce extends BaseService<ActivityGroup, ActivityGroupExample> {

	@Autowired
	private ActivityGroupMapper activityGroupMapper;
	@Autowired
	private ActivityGroupCustomMapper activityGroupCustomMapper;
	
	@Autowired
	public void setActivityGroupMapper(ActivityGroupMapper activityGroupMapper) {
		super.setDao(activityGroupMapper);
		this.activityGroupMapper = activityGroupMapper;
	}
	

    public List<ActivityGroupCustom> selectByCustomExample(ActivityGroupExample activityGroupExample){
    	return activityGroupCustomMapper.selectByCustomExample(activityGroupExample);
    }
    
    public Integer countByCustomExample(ActivityGroupExample activityGroupExample){
 	   return activityGroupCustomMapper.countByCustomExample(activityGroupExample);
    }
    
	public void insertsale(ActivityGroup activityGroup){
		activityGroupMapper.insertSelective(activityGroup);
		
	}
	
	public void updatesale(ActivityGroup activityGroup) {
		activityGroupMapper.updateByPrimaryKeySelective(activityGroup);
}   
}
