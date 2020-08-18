package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityRuleConfigurationCustomMapper;
import com.jf.dao.ActivityRuleConfigurationMapper;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationCustom;
import com.jf.entity.ActivityRuleConfigurationExample;

@Service
@Transactional
public class ActivityRuleConfigurationService extends BaseService<ActivityRuleConfiguration,ActivityRuleConfigurationExample> {
	@Autowired
	private ActivityRuleConfigurationMapper activityRuleConfigurationMapper;
	@Autowired
	private ActivityRuleConfigurationCustomMapper activityRuleConfigurationCustomMapper;
	@Autowired
	public void setActivityRuleConfigurationMapper(ActivityRuleConfigurationMapper activityRuleConfigurationMapper) {
		super.setDao(activityRuleConfigurationMapper);
		this.activityRuleConfigurationMapper = activityRuleConfigurationMapper;
	}
	
	public int countCustomByExample(ActivityRuleConfigurationExample example){
		return activityRuleConfigurationCustomMapper.countByExample(example);
	}
    public List<ActivityRuleConfigurationCustom> selectCustomByExample(ActivityRuleConfigurationExample example){
    	return activityRuleConfigurationCustomMapper.selectByExample(example);
    }
    
    public void updateByEntity(ActivityRuleConfiguration activityRuleConfiguration){
    	activityRuleConfigurationCustomMapper.updateByEntity(activityRuleConfiguration);
    }
}
