package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityAreaPreSellRuleMapper;
import com.jf.dao.ActivityRuleConfigurationCustomMapper;
import com.jf.dao.ActivityRuleConfigurationMapper;
import com.jf.entity.ActivityAreaPreSellRule;
import com.jf.entity.ActivityAreaPreSellRuleExample;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationCustom;
import com.jf.entity.ActivityRuleConfigurationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityAreaPreSellRuleService extends BaseService<ActivityAreaPreSellRule, ActivityAreaPreSellRuleExample> {
	
	@Autowired
	private ActivityAreaPreSellRuleMapper activityAreaPreSellRuleMapper;
	@Autowired
	public void setActivityMapper(ActivityAreaPreSellRuleMapper activityAreaPreSellRuleMapper) {
		super.setDao(activityAreaPreSellRuleMapper);
		this.dao = activityAreaPreSellRuleMapper;
	}
}
