package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
@Repository
public interface CouponCustomMapper{
    int countByExample(CouponExample example);
    List<CouponCustom> selectByExample(CouponExample example);
    CouponCustom selectByPrimaryKey(Integer id);
    //根据活动专区id获取优惠券列表
    List<CouponCustom> selectByActivityAreaIdList(Integer activityAreaId);
    

    Integer platformSum(HashMap<String,Object> map);
    
    Integer commoditySum(HashMap<String,Object> map);
    
    
    Integer effectiveCouponCount(HashMap<String,Object> map);
}