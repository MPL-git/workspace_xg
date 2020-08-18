package com.jf.dao;

import com.jf.entity.PlatformPvStatisticalExt;
import com.jf.entity.PlatformPvStatisticalExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformPvStatisticalExt findById(int id);

    PlatformPvStatisticalExt find(PlatformPvStatisticalExtExample example);

    List<PlatformPvStatisticalExt> list(PlatformPvStatisticalExtExample example);

    List<Integer> listId(PlatformPvStatisticalExtExample example);

    int count(PlatformPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformPvStatisticalExtExample example);

}

