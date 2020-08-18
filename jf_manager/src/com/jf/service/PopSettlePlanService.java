package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PopSettlePlanMapper;
import com.jf.entity.PopSettlePlan;
import com.jf.entity.PopSettlePlanExample;

@Service
@Transactional
public class PopSettlePlanService extends BaseService<PopSettlePlan, PopSettlePlanExample>{
	
	@Autowired
	private PopSettlePlanMapper popSettlePlanMapper;
	@Autowired
	public void setActivityMapper(PopSettlePlanMapper popSettlePlanMapper) {
		super.setDao(popSettlePlanMapper);
		this.popSettlePlanMapper = popSettlePlanMapper;
	}
}
