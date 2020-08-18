package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponCenterBottomNavigationCustom;
import com.jf.entity.CouponCenterBottomNavigationExample;
import com.jf.entity.CouponCombineRecLimitCustom;
import com.jf.entity.CouponCombineRecLimitCustomExample;

@Repository
public interface CouponCenterBottomNavigationCustomMapper {
	
	public List<CouponCenterBottomNavigationCustom> selectByCouponCustomExample(CouponCenterBottomNavigationExample example);
	   
   public Integer countByCouponCustomExample(CouponCenterBottomNavigationExample example);

}
