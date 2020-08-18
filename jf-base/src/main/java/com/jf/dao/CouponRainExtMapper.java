package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CouponRainExt;
import com.jf.entity.CouponRainExtExample;

@Repository
public interface CouponRainExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponRainExt findById(int id);

    CouponRainExt find(CouponRainExtExample example);

    List<CouponRainExt> list(CouponRainExtExample example);

    List<Integer> listId(CouponRainExtExample example);

    int count(CouponRainExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponRainExtExample example);

    int max(@Param("field") String field, @Param("example") CouponRainExtExample example);

    int min(@Param("field") String field, @Param("example") CouponRainExtExample example);

}

