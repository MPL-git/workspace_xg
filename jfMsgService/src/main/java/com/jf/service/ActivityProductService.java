package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityProductMapper;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;

@Service
@Transactional
public class ActivityProductService extends BaseService<ActivityProduct, ActivityProductExample> {
	@Autowired
	private ActivityProductMapper activityProductMapper;

	@Autowired
	public void setActivityProductMapper(ActivityProductMapper activityProductMapper) {
		this.setDao(activityProductMapper);
		this.activityProductMapper = activityProductMapper;
	}
	
}
