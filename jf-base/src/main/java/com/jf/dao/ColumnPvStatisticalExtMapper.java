package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.ColumnPvStatisticalExt;
import com.jf.entity.ColumnPvStatisticalExtExample;

@Repository
public interface ColumnPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ColumnPvStatisticalExt findById(int id);

    ColumnPvStatisticalExt find(ColumnPvStatisticalExtExample example);

    List<ColumnPvStatisticalExt> list(ColumnPvStatisticalExtExample example);

    List<Integer> listId(ColumnPvStatisticalExtExample example);

    int count(ColumnPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") ColumnPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") ColumnPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") ColumnPvStatisticalExtExample example);

}

