package com.jf.dao;

import com.jf.entity.ActivityAuthExt;
import com.jf.entity.ActivityAuthExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAuthExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAuthExt findById(int id);

    ActivityAuthExt find(ActivityAuthExtExample example);

    List<ActivityAuthExt> list(ActivityAuthExtExample example);

    List<Integer> listId(ActivityAuthExtExample example);

    int count(ActivityAuthExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAuthExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAuthExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAuthExtExample example);

}

