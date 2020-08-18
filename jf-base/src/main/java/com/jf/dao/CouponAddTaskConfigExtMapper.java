package com.jf.dao;

import com.jf.entity.CouponAddTaskConfigExt;
import com.jf.entity.CouponAddTaskConfigExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponAddTaskConfigExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CouponAddTaskConfigExt findById(int id);

    CouponAddTaskConfigExt find(CouponAddTaskConfigExtExample example);

    List<CouponAddTaskConfigExt> list(CouponAddTaskConfigExtExample example);

    List<Integer> listId(CouponAddTaskConfigExtExample example);

    int count(CouponAddTaskConfigExtExample example);

    long sum(@Param("field") String field, @Param("example") CouponAddTaskConfigExtExample example);

    int max(@Param("field") String field, @Param("example") CouponAddTaskConfigExtExample example);

    int min(@Param("field") String field, @Param("example") CouponAddTaskConfigExtExample example);

}

