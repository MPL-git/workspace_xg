package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCombineRecLimitCustomMapper;
import com.jf.dao.CouponCombineRecLimitMapper;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitCustom;
import com.jf.entity.CouponCombineRecLimitCustomExample;
import com.jf.entity.CouponCombineRecLimitExample;

@Service
@Transactional
public class CouponCombineRecLimitService extends BaseService<CouponCombineRecLimit, CouponCombineRecLimitExample> {

	@Autowired
	private CouponCombineRecLimitMapper couponCombineRecLimitMapper;
	
	@Autowired
	private CouponCombineRecLimitCustomMapper couponCombineRecLimitCustomMapper;
	
	@Autowired
	public void setCouponCombineRecLimitMapper(CouponCombineRecLimitMapper couponCombineRecLimitMapper) {
		super.setDao(couponCombineRecLimitMapper);
		this.dao = couponCombineRecLimitMapper;
	}
	
	public List<CouponCombineRecLimitCustom> selectByCouponCombineRecLimitCustomExample(CouponCombineRecLimitCustomExample example) {
		return couponCombineRecLimitCustomMapper.selectByCouponCombineRecLimitCustomExample(example);
	}

    public CouponCombineRecLimitCustom selectByCustomPrimaryKey(Integer id) {
    	return couponCombineRecLimitCustomMapper.selectByCouponCombineRecLimitPrimaryKey(id);
    }
    
    public int countByCouponCombineRecLimitCustomExample(CouponCombineRecLimitCustomExample example) {
    	return couponCombineRecLimitCustomMapper.countByCouponCombineRecLimitCustomExample(example);
    }
	
}
