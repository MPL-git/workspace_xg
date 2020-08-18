package com.jf.dao;

import com.jf.entity.IntegralGiveExt;
import com.jf.entity.IntegralGiveExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralGiveExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntegralGiveExt findById(int id);

    IntegralGiveExt find(IntegralGiveExtExample example);

    List<IntegralGiveExt> list(IntegralGiveExtExample example);

    List<Integer> listId(IntegralGiveExtExample example);

    int count(IntegralGiveExtExample example);

    long sum(@Param("field") String field, @Param("example") IntegralGiveExtExample example);

    int max(@Param("field") String field, @Param("example") IntegralGiveExtExample example);

    int min(@Param("field") String field, @Param("example") IntegralGiveExtExample example);

}

