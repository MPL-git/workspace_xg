package com.jf.dao;

import com.jf.entity.IntegralDtlExt;
import com.jf.entity.IntegralDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntegralDtlExt findById(int id);

    IntegralDtlExt find(IntegralDtlExtExample example);

    List<IntegralDtlExt> list(IntegralDtlExtExample example);

    List<Integer> listId(IntegralDtlExtExample example);

    int count(IntegralDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") IntegralDtlExtExample example);

    int max(@Param("field") String field, @Param("example") IntegralDtlExtExample example);

    int min(@Param("field") String field, @Param("example") IntegralDtlExtExample example);

}

