package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponRainSetupCustomMapper;
import com.jf.dao.CouponRainSetupMapper;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupCustom;
import com.jf.entity.CouponRainSetupCustomExample;
import com.jf.entity.CouponRainSetupExample;

@Service
@Transactional
public class CouponRainSetupService extends BaseService<CouponRainSetup,CouponRainSetupExample> {
	@Autowired
	private CouponRainSetupMapper couponRainSetupMapper;
	@Autowired
	private CouponRainSetupCustomMapper couponRainSetupCustomMapper;
	
	@Autowired
	public void setCouponRainMapper(CouponRainSetupMapper couponRainSetupMapper) {
		super.setDao(couponRainSetupMapper);
		this.couponRainSetupMapper = couponRainSetupMapper;
	}
	
	public int countByCouponRainSetupCustomExample(CouponRainSetupCustomExample example){
		return couponRainSetupCustomMapper.countByCouponRainSetupCustomExample(example);
	}
    public List<CouponRainSetupCustom> selectByCouponRainSetupCustomExample(CouponRainSetupCustomExample example){
    	return couponRainSetupCustomMapper.selectByCouponRainSetupCustomExample(example);
    }
    public CouponRainSetupCustom selectByCouponRainSetupCustomPrimaryKey(Integer id){
    	return couponRainSetupCustomMapper.selectByCouponRainSetupCustomPrimaryKey(id);
    }

}
