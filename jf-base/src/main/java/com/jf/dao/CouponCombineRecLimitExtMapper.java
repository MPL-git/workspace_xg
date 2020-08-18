package com.jf.dao;

import com.jf.entity.CouponCombineRecLimitExt;
import com.jf.entity.CouponCombineRecLimitExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCombineRecLimitExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponCombineRecLimitExt findById(int id);

    CouponCombineRecLimitExt find(CouponCombineRecLimitExtExample example);

    List<CouponCombineRecLimitExt> list(CouponCombineRecLimitExtExample example);

    List<Integer> listId(CouponCombineRecLimitExtExample example);

    int count(CouponCombineRecLimitExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponCombineRecLimitExtExample example);

    int max(@Param("field") String field, @Param("example") CouponCombineRecLimitExtExample example);

    int min(@Param("field") String field, @Param("example") CouponCombineRecLimitExtExample example);

}

