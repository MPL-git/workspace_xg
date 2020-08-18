package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityAreaPreSellRuleMapper;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.entity.ActivityAreaPreSellRule;
import com.jf.entity.ActivityAreaPreSellRuleExample;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;
import com.jf.entity.ActivityProductDepositExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityProductDepositService extends BaseService<ActivityProductDeposit, ActivityProductDepositExample> {
	
	@Autowired
	private ActivityProductDepositMapper activityProductDepositMapper;
	@Autowired
	public void setActivityMapper(ActivityProductDepositMapper activityProductDepositMapper) {
		super.setDao(activityProductDepositMapper);
		this.dao = activityProductDepositMapper;
	}
}
