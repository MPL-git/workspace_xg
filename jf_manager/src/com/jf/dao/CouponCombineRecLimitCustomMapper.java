package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponCombineRecLimitCustom;
import com.jf.entity.CouponCombineRecLimitCustomExample;
@Repository
public interface CouponCombineRecLimitCustomMapper{

   public List<CouponCombineRecLimitCustom> selectByCouponCombineRecLimitCustomExample(CouponCombineRecLimitCustomExample example);
   
   public Integer countByCouponCombineRecLimitCustomExample(CouponCombineRecLimitCustomExample example);
   
   CouponCombineRecLimitCustom selectByCouponCombineRecLimitPrimaryKey(Integer id);
  
}