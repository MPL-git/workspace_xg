package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityProductDepositMapper;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;

@Service
@Transactional
public class ActivityProductDepositService extends BaseService<ActivityProductDeposit, ActivityProductDepositExample> {

	@Autowired
	private ActivityProductDepositMapper activityProductDepositMapper;
	
	@Autowired
	public void setActivityProductDepositMapper(ActivityProductDepositMapper activityProductDepositMapper) {
		this.setDao(activityProductDepositMapper);
		this.activityProductDepositMapper = activityProductDepositMapper;
	}
	
}
