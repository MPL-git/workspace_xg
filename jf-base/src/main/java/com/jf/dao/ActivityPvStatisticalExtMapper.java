package com.jf.dao;

import com.jf.entity.ActivityPvStatisticalExt;
import com.jf.entity.ActivityPvStatisticalExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityPvStatisticalExt findById(int id);

    ActivityPvStatisticalExt find(ActivityPvStatisticalExtExample example);

    List<ActivityPvStatisticalExt> list(ActivityPvStatisticalExtExample example);

    List<Integer> listId(ActivityPvStatisticalExtExample example);

    int count(ActivityPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityPvStatisticalExtExample example);

}

