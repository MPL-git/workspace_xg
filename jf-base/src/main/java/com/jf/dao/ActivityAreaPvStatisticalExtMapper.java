package com.jf.dao;

import com.jf.entity.ActivityAreaPvStatisticalExt;
import com.jf.entity.ActivityAreaPvStatisticalExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAreaPvStatisticalExt findById(int id);

    ActivityAreaPvStatisticalExt find(ActivityAreaPvStatisticalExtExample example);

    List<ActivityAreaPvStatisticalExt> list(ActivityAreaPvStatisticalExtExample example);

    List<Integer> listId(ActivityAreaPvStatisticalExtExample example);

    int count(ActivityAreaPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAreaPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAreaPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAreaPvStatisticalExtExample example);

}

