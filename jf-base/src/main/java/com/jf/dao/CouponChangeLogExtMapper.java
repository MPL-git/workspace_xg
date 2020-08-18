package com.jf.dao;

import com.jf.entity.CouponChangeLogExt;
import com.jf.entity.CouponChangeLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponChangeLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponChangeLogExt findById(int id);

    CouponChangeLogExt find(CouponChangeLogExtExample example);

    List<CouponChangeLogExt> list(CouponChangeLogExtExample example);

    List<Integer> listId(CouponChangeLogExtExample example);

    int count(CouponChangeLogExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponChangeLogExtExample example);

    int max(@Param("field") String field, @Param("example") CouponChangeLogExtExample example);

    int min(@Param("field") String field, @Param("example") CouponChangeLogExtExample example);

}

