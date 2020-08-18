package com.jf.dao;

import com.jf.entity.GrowthDtlExt;
import com.jf.entity.GrowthDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    GrowthDtlExt findById(int id);

    GrowthDtlExt find(GrowthDtlExtExample example);

    List<GrowthDtlExt> list(GrowthDtlExtExample example);

    List<Integer> listId(GrowthDtlExtExample example);

    int count(GrowthDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") GrowthDtlExtExample example);

    int max(@Param("field") String field, @Param("example") GrowthDtlExtExample example);

    int min(@Param("field") String field, @Param("example") GrowthDtlExtExample example);

}

