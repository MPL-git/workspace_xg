package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityRuleConfigurationCustomMapper;
import com.jf.dao.ActivityRuleConfigurationMapper;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationCustom;
import com.jf.entity.ActivityRuleConfigurationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityRuleConfigurationService extends BaseService<ActivityRuleConfiguration,ActivityRuleConfigurationExample> {
	
	@Autowired
	private ActivityRuleConfigurationCustomMapper activityRuleConfigurationCustomMapper;
	@Autowired
	public void setActivityMapper(ActivityRuleConfigurationMapper activityRuleConfigurationMapper) {
		super.setDao(activityRuleConfigurationMapper);
		this.dao = activityRuleConfigurationMapper;
	}

	public List<ActivityRuleConfigurationCustom> selectCustomByExample(
			ActivityRuleConfigurationExample e) {
		// TODO Auto-generated method stub
		return activityRuleConfigurationCustomMapper.selectCustomByExample(e);
	}
}
