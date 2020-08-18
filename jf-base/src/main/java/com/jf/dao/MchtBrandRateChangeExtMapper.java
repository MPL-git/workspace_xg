package com.jf.dao;

import com.jf.entity.MchtBrandRateChangeExt;
import com.jf.entity.MchtBrandRateChangeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandRateChangeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandRateChangeExt findById(int id);

    MchtBrandRateChangeExt find(MchtBrandRateChangeExtExample example);

    List<MchtBrandRateChangeExt> list(MchtBrandRateChangeExtExample example);

    List<Integer> listId(MchtBrandRateChangeExtExample example);

    int count(MchtBrandRateChangeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandRateChangeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandRateChangeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandRateChangeExtExample example);

}

