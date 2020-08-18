package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CouponExt;
import com.jf.entity.CouponExtExample;

@Repository
public interface CouponExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponExt findById(int id);

    CouponExt find(CouponExtExample example);

    List<CouponExt> list(CouponExtExample example);

    List<Integer> listId(CouponExtExample example);

    int count(CouponExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponExtExample example);

    int max(@Param("field") String field, @Param("example") CouponExtExample example);

    int min(@Param("field") String field, @Param("example") CouponExtExample example);

}

