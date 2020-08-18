package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponExample;
@Repository
public interface MemberCouponCustomMapper{
    int countByExample(MemberCouponExample example);
    List<MemberCouponCustom> selectByExample(MemberCouponExample example);
    MemberCouponCustom selectByPrimaryKey(Integer id);
}