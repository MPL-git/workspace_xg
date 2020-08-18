package com.jf.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCustomMapper;
import com.jf.dao.CouponMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;

@Service
@Transactional
public class CouponService extends BaseService<Coupon,CouponExample> {
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private CouponCustomMapper couponCustomMapper;
	
	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		super.setDao(couponMapper);
		this.couponMapper = couponMapper;
	}
	
	public int countCouponCustomByExample(CouponExample example){
		return couponCustomMapper.countByExample(example);
	}
    public List<CouponCustom> selectCouponCustomByExample(CouponExample example){
    	return couponCustomMapper.selectByExample(example);
    }
    public CouponCustom selectCouponCustomByPrimaryKey(Integer id){
    	return couponCustomMapper.selectByPrimaryKey(id);
    }
    
    public List<CouponCustom> selectByActivityAreaIdList(Integer activityAreaId){
    	return couponCustomMapper.selectByActivityAreaIdList(activityAreaId);
    }
    public int platformSum(HashMap<String,Object> map){
		return couponCustomMapper.platformSum(map);
	}
    
    public int commoditySum(HashMap<String,Object> map){
		return couponCustomMapper.commoditySum(map);
	}
    public int effectiveCouponCount(HashMap<String,Object> map){
		return couponCustomMapper.effectiveCouponCount(map);
	}
	public void updateByCouponIds(String couponIds, String recBeginDate,String recEndDate) throws ParseException {
		String[] couponIdsArray = couponIds.split(",");
		for(String couponId:couponIdsArray){
			Coupon coupon = couponMapper.selectByPrimaryKey(Integer.parseInt(couponId));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			coupon.setRecBeginDate(sdf.parse(recBeginDate));
			coupon.setRecEndDate(sdf.parse(recEndDate));
			couponMapper.updateByPrimaryKey(coupon);
		}
	}
}
