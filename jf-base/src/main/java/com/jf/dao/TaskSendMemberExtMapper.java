package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.TaskSendMemberExt;
import com.jf.entity.TaskSendMemberExtExample;

@Repository
public interface TaskSendMemberExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TaskSendMemberExt findById(int id);

    TaskSendMemberExt find(TaskSendMemberExtExample example);

    List<TaskSendMemberExt> list(TaskSendMemberExtExample example);

    List<Integer> listId(TaskSendMemberExtExample example);

    int count(TaskSendMemberExtExample example);

    long sum(@Param("field") String field, @Param("example") TaskSendMemberExtExample example);

    int max(@Param("field") String field, @Param("example") TaskSendMemberExtExample example);

    int min(@Param("field") String field, @Param("example") TaskSendMemberExtExample example);

}

