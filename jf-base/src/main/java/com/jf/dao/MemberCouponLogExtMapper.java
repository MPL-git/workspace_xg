package com.jf.dao;

import com.jf.entity.MemberCouponLogExt;
import com.jf.entity.MemberCouponLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCouponLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberCouponLogExt findById(int id);

    MemberCouponLogExt find(MemberCouponLogExtExample example);

    List<MemberCouponLogExt> list(MemberCouponLogExtExample example);

    List<Integer> listId(MemberCouponLogExtExample example);

    int count(MemberCouponLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberCouponLogExtExample example);

    int max(@Param("field") String field, @Param("example") MemberCouponLogExtExample example);

    int min(@Param("field") String field, @Param("example") MemberCouponLogExtExample example);

}

