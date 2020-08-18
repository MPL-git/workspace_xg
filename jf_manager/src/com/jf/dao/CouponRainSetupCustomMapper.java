package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponRainSetupCustom;
import com.jf.entity.CouponRainSetupCustomExample;
@Repository
public interface CouponRainSetupCustomMapper{

   public List<CouponRainSetupCustom> selectByCouponRainSetupCustomExample(CouponRainSetupCustomExample example);
   
   public Integer countByCouponRainSetupCustomExample(CouponRainSetupCustomExample example);
   
   CouponRainSetupCustom selectByCouponRainSetupCustomPrimaryKey(Integer id);
  
}