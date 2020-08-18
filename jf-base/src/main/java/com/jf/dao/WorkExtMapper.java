package com.jf.dao;

import com.jf.entity.WorkExt;
import com.jf.entity.WorkExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WorkExt findById(int id);

    WorkExt find(WorkExtExample example);

    List<WorkExt> list(WorkExtExample example);

    List<Integer> listId(WorkExtExample example);

    int count(WorkExtExample example);

    long sum(@Param("field") String field, @Param("example") WorkExtExample example);

    int max(@Param("field") String field, @Param("example") WorkExtExample example);

    int min(@Param("field") String field, @Param("example") WorkExtExample example);

}

