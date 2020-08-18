package com.jf.dao;

import com.jf.entity.CouponExchangeCodeExt;
import com.jf.entity.CouponExchangeCodeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponExchangeCodeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponExchangeCodeExt findById(int id);

    CouponExchangeCodeExt find(CouponExchangeCodeExtExample example);

    List<CouponExchangeCodeExt> list(CouponExchangeCodeExtExample example);

    List<Integer> listId(CouponExchangeCodeExtExample example);

    int count(CouponExchangeCodeExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponExchangeCodeExtExample example);

    int max(@Param("field") String field, @Param("example") CouponExchangeCodeExtExample example);

    int min(@Param("field") String field, @Param("example") CouponExchangeCodeExtExample example);

}

