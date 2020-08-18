package com.jf.dao;

import com.jf.entity.TaskLogExt;
import com.jf.entity.TaskLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaskLogExt findById(int id);

    TaskLogExt find(TaskLogExtExample example);

    List<TaskLogExt> list(TaskLogExtExample example);

    List<Integer> listId(TaskLogExtExample example);

    int count(TaskLogExtExample example);

    long sum(@Param("field") String field, @Param("example") TaskLogExtExample example);

    int max(@Param("field") String field, @Param("example") TaskLogExtExample example);

    int min(@Param("field") String field, @Param("example") TaskLogExtExample example);

}

