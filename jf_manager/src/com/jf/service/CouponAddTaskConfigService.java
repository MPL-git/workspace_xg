package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponAddtaskConfigMapper;
import com.jf.entity.CouponAddtaskConfig;
import com.jf.entity.CouponAddtaskConfigExample;

@Service
@Transactional
public class CouponAddTaskConfigService extends BaseService<CouponAddtaskConfig,CouponAddtaskConfigExample> {
	@Autowired
	private CouponAddtaskConfigMapper couponAddtaskConfigMapper;

	@Autowired
	public void setCouponAddTaskConfigMapper(CouponAddtaskConfigMapper couponAddtaskConfigMapper) {
		super.setDao(couponAddtaskConfigMapper);
		this.couponAddtaskConfigMapper = couponAddtaskConfigMapper;
	}
	
}
