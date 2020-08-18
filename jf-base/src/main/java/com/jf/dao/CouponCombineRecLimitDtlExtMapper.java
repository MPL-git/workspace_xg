package com.jf.dao;

import com.jf.entity.CouponCombineRecLimitDtlExt;
import com.jf.entity.CouponCombineRecLimitDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCombineRecLimitDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponCombineRecLimitDtlExt findById(int id);

    CouponCombineRecLimitDtlExt find(CouponCombineRecLimitDtlExtExample example);

    List<CouponCombineRecLimitDtlExt> list(CouponCombineRecLimitDtlExtExample example);

    List<Integer> listId(CouponCombineRecLimitDtlExtExample example);

    int count(CouponCombineRecLimitDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponCombineRecLimitDtlExtExample example);

    int max(@Param("field") String field, @Param("example") CouponCombineRecLimitDtlExtExample example);

    int min(@Param("field") String field, @Param("example") CouponCombineRecLimitDtlExtExample example);

}

