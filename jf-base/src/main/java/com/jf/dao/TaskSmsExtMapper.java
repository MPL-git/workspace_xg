package com.jf.dao;

import com.jf.entity.TaskSmsExt;
import com.jf.entity.TaskSmsExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskSmsExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaskSmsExt findById(int id);

    TaskSmsExt find(TaskSmsExtExample example);

    List<TaskSmsExt> list(TaskSmsExtExample example);

    List<Integer> listId(TaskSmsExtExample example);

    int count(TaskSmsExtExample example);

    long sum(@Param("field") String field, @Param("example") TaskSmsExtExample example);

    int max(@Param("field") String field, @Param("example") TaskSmsExtExample example);

    int min(@Param("field") String field, @Param("example") TaskSmsExtExample example);

}

