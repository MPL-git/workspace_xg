package com.jf.dao;

import com.jf.entity.ActivityAreaExt;
import com.jf.entity.ActivityAreaExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAreaExt findById(int id);

    ActivityAreaExt find(ActivityAreaExtExample example);

    List<ActivityAreaExt> list(ActivityAreaExtExample example);

    List<Integer> listId(ActivityAreaExtExample example);

    int count(ActivityAreaExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAreaExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAreaExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAreaExtExample example);

}

