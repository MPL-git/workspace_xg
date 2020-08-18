package com.jf.dao;

import com.jf.entity.ActivityExt;
import com.jf.entity.ActivityExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityExt findById(int id);

    ActivityExt find(ActivityExtExample example);

    List<ActivityExt> list(ActivityExtExample example);

    List<Integer> listId(ActivityExtExample example);

    int count(ActivityExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityExtExample example);

}

