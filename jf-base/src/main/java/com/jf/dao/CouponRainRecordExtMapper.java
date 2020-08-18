package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CouponRainRecordExt;
import com.jf.entity.CouponRainRecordExtExample;

@Repository
public interface CouponRainRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponRainRecordExt findById(int id);

    CouponRainRecordExt find(CouponRainRecordExtExample example);

    List<CouponRainRecordExt> list(CouponRainRecordExtExample example);

    List<Integer> listId(CouponRainRecordExtExample example);

    int count(CouponRainRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponRainRecordExtExample example);

    int max(@Param("field") String field, @Param("example") CouponRainRecordExtExample example);

    int min(@Param("field") String field, @Param("example") CouponRainRecordExtExample example);

}

