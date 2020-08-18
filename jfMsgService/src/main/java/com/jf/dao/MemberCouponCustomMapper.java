package com.jf.dao;

import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCouponCustomMapper{
    List<Integer> selectByCustomExample(@Param("couponIdList") List<Integer> couponIdList,@Param("limitNumber") Integer limitNumber);
}
