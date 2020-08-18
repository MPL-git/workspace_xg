package com.jf.dao;

import com.jf.entity.MemberCouponExt;
import com.jf.entity.MemberCouponExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCouponExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberCouponExt findById(int id);

    MemberCouponExt find(MemberCouponExtExample example);

    List<MemberCouponExt> list(MemberCouponExtExample example);

    List<Integer> listId(MemberCouponExtExample example);

    int count(MemberCouponExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberCouponExtExample example);

    int max(@Param("field") String field, @Param("example") MemberCouponExtExample example);

    int min(@Param("field") String field, @Param("example") MemberCouponExtExample example);

}

