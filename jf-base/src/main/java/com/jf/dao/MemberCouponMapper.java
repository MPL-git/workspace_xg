package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCouponMapper extends BaseDao<MemberCoupon, MemberCouponExample> {
    int countByExample(MemberCouponExample example);

    int deleteByExample(MemberCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCoupon record);

    int insertSelective(MemberCoupon record);

    List<MemberCoupon> selectByExample(MemberCouponExample example);

    MemberCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCoupon record, @Param("example") MemberCouponExample example);

    int updateByExample(@Param("record") MemberCoupon record, @Param("example") MemberCouponExample example);

    int updateByPrimaryKeySelective(MemberCoupon record);

    int updateByPrimaryKey(MemberCoupon record);
}
