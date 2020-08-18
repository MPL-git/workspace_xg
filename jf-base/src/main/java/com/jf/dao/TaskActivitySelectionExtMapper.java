package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.TaskActivitySelectionExt;
import com.jf.entity.TaskActivitySelectionExtExample;

@Repository
public interface TaskActivitySelectionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaskActivitySelectionExt findById(int id);

    TaskActivitySelectionExt find(TaskActivitySelectionExtExample example);

    List<TaskActivitySelectionExt> list(TaskActivitySelectionExtExample example);

    List<Integer> listId(TaskActivitySelectionExtExample example);

    int count(TaskActivitySelectionExtExample example);

    long sum(@Param("field") String field, @Param("example") TaskActivitySelectionExtExample example);

    int max(@Param("field") String field, @Param("example") TaskActivitySelectionExtExample example);

    int min(@Param("field") String field, @Param("example") TaskActivitySelectionExtExample example);

}

