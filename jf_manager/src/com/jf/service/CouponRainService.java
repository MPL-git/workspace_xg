package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponRainCustomMapper;
import com.jf.dao.CouponRainMapper;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainCustom;
import com.jf.entity.CouponRainCustomExample;
import com.jf.entity.CouponRainExample;

@Service
@Transactional
public class CouponRainService extends BaseService<CouponRain,CouponRainExample> {
	@Autowired
	private CouponRainMapper couponRainMapper;
	@Autowired
	private CouponRainCustomMapper couponRainCustomMapper;
	
	@Autowired
	public void setCouponRainMapper(CouponRainMapper couponRainMapper) {
		super.setDao(couponRainMapper);
		this.couponRainMapper = couponRainMapper;
	}
	
	public int countByCouponRainCustomExample(CouponRainCustomExample example){
		return couponRainCustomMapper.countByCouponRainCustomExample(example);
	}
    public List<CouponRainCustom> selectByCouponRainCustomExample(CouponRainCustomExample example){
    	return couponRainCustomMapper.selectByCouponRainCustomExample(example);
    }
    public CouponRainCustom selectCouponCustomByPrimaryKey(Integer id){
    	return couponRainCustomMapper.selectByCouponRainCustomPrimaryKey(id);
    }

}
