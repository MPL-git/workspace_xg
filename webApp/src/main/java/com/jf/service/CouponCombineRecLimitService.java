package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCombineRecLimitMapper;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitExample;

@Service
@Transactional
public class CouponCombineRecLimitService extends BaseService<CouponCombineRecLimit, CouponCombineRecLimitExample> {
	@Autowired
	private CouponCombineRecLimitMapper couponCombineRecLimitMapper;
	@Autowired
	public void setCouponCombineRecLimitMapper(CouponCombineRecLimitMapper couponCombineRecLimitMapper) {
		this.setDao(couponCombineRecLimitMapper);
		this.couponCombineRecLimitMapper = couponCombineRecLimitMapper;
	}
	
	
}
