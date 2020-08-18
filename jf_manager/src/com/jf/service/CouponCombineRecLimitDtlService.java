package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCombineRecLimitDtlMapper;
import com.jf.entity.CouponCombineRecLimitDtl;
import com.jf.entity.CouponCombineRecLimitDtlExample;

@Service
@Transactional
public class CouponCombineRecLimitDtlService extends BaseService<CouponCombineRecLimitDtl, CouponCombineRecLimitDtlExample> {

	@Autowired
	private CouponCombineRecLimitDtlMapper couponCombineRecLimitDtlMapper;

	@Autowired
	public void setCouponCombineRecLimitDtlMapper(CouponCombineRecLimitDtlMapper couponCombineRecLimitDtlMapper) {
		super.setDao(couponCombineRecLimitDtlMapper);
		this.dao = couponCombineRecLimitDtlMapper;
	}
	
}
