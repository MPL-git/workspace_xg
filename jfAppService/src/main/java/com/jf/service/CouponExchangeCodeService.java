package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CouponExchangeCodeMapper;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年1月25日 下午6:23:14 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CouponExchangeCodeService extends BaseService<CouponExchangeCode, CouponExchangeCodeExample> {
	@Autowired
	private CouponExchangeCodeMapper couponExchangeCodeMapper;

	@Autowired
	public void setCouponExchangeCodeMapper(CouponExchangeCodeMapper couponExchangeCodeMapper) {
		this.setDao(couponExchangeCodeMapper);
		this.couponExchangeCodeMapper = couponExchangeCodeMapper;
	}
	
	
}
