package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponRainCustom;
import com.jf.entity.CouponRainCustomExample;
@Repository
public interface CouponRainCustomMapper{

   public List<CouponRainCustom> selectByCouponRainCustomExample(CouponRainCustomExample example);
   
   public Integer countByCouponRainCustomExample(CouponRainCustomExample example);
   
   CouponRainCustom selectByCouponRainCustomPrimaryKey(Integer id);
  
}