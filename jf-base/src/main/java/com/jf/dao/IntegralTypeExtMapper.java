package com.jf.dao;

import com.jf.entity.IntegralTypeExt;
import com.jf.entity.IntegralTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntegralTypeExt findById(int id);

    IntegralTypeExt find(IntegralTypeExtExample example);

    List<IntegralTypeExt> list(IntegralTypeExtExample example);

    List<Integer> listId(IntegralTypeExtExample example);

    int count(IntegralTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") IntegralTypeExtExample example);

    int max(@Param("field") String field, @Param("example") IntegralTypeExtExample example);

    int min(@Param("field") String field, @Param("example") IntegralTypeExtExample example);

}

