package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.MemberCoupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponCustomExample;
@Repository
public interface CouponCustomMapper {
	
	public int countByCustomExample(CouponCustomExample example);

    public List<CouponCustom> selectByCustomExample(CouponCustomExample example);

    public CouponCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") Coupon record, @Param("example") CouponCustomExample example);

    public int addGrantQuantity(@Param("activityType") String activityType);

    void batchGiveCoupontoMember(@Param("resultMapList") List<Map<String, Object>> resultMapList,@Param("memberCoupon") MemberCoupon memberCoupon);

    void updateCouponRecQuantity(@Param("size") int size,@Param("couponId") int couponId);
}