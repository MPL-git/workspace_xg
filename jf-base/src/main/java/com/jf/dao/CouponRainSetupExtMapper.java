package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CouponRainSetupExt;
import com.jf.entity.CouponRainSetupExtExample;

@Repository
public interface CouponRainSetupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponRainSetupExt findById(int id);

    CouponRainSetupExt find(CouponRainSetupExtExample example);

    List<CouponRainSetupExt> list(CouponRainSetupExtExample example);

    List<Integer> listId(CouponRainSetupExtExample example);

    int count(CouponRainSetupExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponRainSetupExtExample example);

    int max(@Param("field") String field, @Param("example") CouponRainSetupExtExample example);

    int min(@Param("field") String field, @Param("example") CouponRainSetupExtExample example);

}

