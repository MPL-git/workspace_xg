package com.jf.dao;

import com.jf.entity.ActivityGroupExt;
import com.jf.entity.ActivityGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityGroupExt findById(int id);

    ActivityGroupExt find(ActivityGroupExtExample example);

    List<ActivityGroupExt> list(ActivityGroupExtExample example);

    List<Integer> listId(ActivityGroupExtExample example);

    int count(ActivityGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityGroupExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityGroupExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityGroupExtExample example);

}

