package com.jf.service;

import com.jf.dao.ActivityAreaPreSellRuleMapper;
import com.jf.entity.ActivityAreaPreSellRule;
import com.jf.entity.ActivityAreaPreSellRuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
