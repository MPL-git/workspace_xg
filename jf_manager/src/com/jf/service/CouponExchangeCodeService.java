package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponExchangeCodeCustomMapper;
import com.jf.dao.CouponExchangeCodeMapper;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeCustom;
import com.jf.entity.CouponExchangeCodeCustomExample;
import com.jf.entity.CouponExchangeCodeExample;

@Service
@Transactional
public class CouponExchangeCodeService extends BaseService<CouponExchangeCode, CouponExchangeCodeExample> {

	@Autowired
	private CouponExchangeCodeMapper couponExchangeCodeMapper;
	
	@Autowired
	private CouponExchangeCodeCustomMapper couponExchangeCodeCustomMapper;
	
	@Autowired
	public void setCouponExchangeCodeMapper(CouponExchangeCodeMapper couponExchangeCodeMapper) {
		super.setDao(couponExchangeCodeMapper);
		this.dao = couponExchangeCodeMapper;
	}
	
	public List<CouponExchangeCodeCustom> selectByCustomExample(CouponExchangeCodeCustomExample example) {
		return couponExchangeCodeCustomMapper.selectByCustomExample(example);
	}

    public CouponExchangeCodeCustom selectByCustomPrimaryKey(Integer id) {
    	return couponExchangeCodeCustomMapper.selectByCustomPrimaryKey(id);
    }
    
    public int countByCustomExample(CouponExchangeCodeCustomExample example) {
    	return couponExchangeCodeCustomMapper.countByCustomExample(example);
    }
	
}
