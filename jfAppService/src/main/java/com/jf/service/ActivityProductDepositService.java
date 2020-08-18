package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityProductDepositService extends BaseService<ActivityProductDeposit, ActivityProductDepositExample> {

	@Autowired
	private ActivityProductDepositMapper activityProductDepositMapper;
	
	@Autowired
	public void setActivityProductDepositMapper(ActivityProductDepositMapper activityProductDepositMapper) {
		super.setDao(activityProductDepositMapper);
		this.activityProductDepositMapper = activityProductDepositMapper;
	}
	
	
	
}
