package com.jf.dao;

import com.jf.entity.TaskExt;
import com.jf.entity.TaskExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaskExt findById(int id);

    TaskExt find(TaskExtExample example);

    List<TaskExt> list(TaskExtExample example);

    List<Integer> listId(TaskExtExample example);

    int count(TaskExtExample example);

    long sum(@Param("field") String field, @Param("example") TaskExtExample example);

    int max(@Param("field") String field, @Param("example") TaskExtExample example);

    int min(@Param("field") String field, @Param("example") TaskExtExample example);

}

