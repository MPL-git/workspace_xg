package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.entity.MemberCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCustomMapper;
import com.jf.dao.CouponMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;

import java.util.List;
import java.util.Map;


/**
  * @author  chenwf: 
  * @date 创建时间：2018年8月8日 下午2:01:22 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CouponService extends BaseService<Coupon,CouponExample> {
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private CouponCustomMapper couponCustomMapper;

	@Autowired
	public void setCouponMapper(CouponMapper couponMapper) {
		this.setDao(couponMapper);
		this.couponMapper = couponMapper;
	}
	
	public int addGrantQuantity(String activityType) {
		return couponCustomMapper.addGrantQuantity(activityType);
	}


    public void batchGiveCoupontoMember(List<Map<String, Object>> resultMapList, MemberCoupon memberCoupon) {
		//优惠券已领量+发放数量
		couponCustomMapper.updateCouponRecQuantity(resultMapList.size(), memberCoupon.getCouponId());
		//优惠券发放到用户
		couponCustomMapper.batchGiveCoupontoMember(resultMapList, memberCoupon);
    }
}
