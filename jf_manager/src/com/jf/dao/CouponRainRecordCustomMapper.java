package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponRainRecordCustom;
import com.jf.entity.CouponRainRecordCustomExample;
@Repository
public interface CouponRainRecordCustomMapper{

   public List<CouponRainRecordCustom> selectByCouponRainRecordCustomExample(CouponRainRecordCustomExample example);
   
   public Integer countByCouponRainRecordCustomExample(CouponRainRecordCustomExample example);
   
   CouponRainRecordCustom selectByCouponRainRecordCustomPrimaryKey(Integer id);
  
}