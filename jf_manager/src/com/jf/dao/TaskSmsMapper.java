package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsExample;

@Repository
public interface TaskSmsMapper extends BaseDao<TaskSms, TaskSmsExample> {
    int countByExample(TaskSmsExample example);

    int deleteByExample(TaskSmsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskSms record);

    int insertSelective(TaskSms record);

    List<TaskSms> selectByExample(TaskSmsExample example);

    TaskSms selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskSms record, @Param("example") TaskSmsExample example);

    int updateByExample(@Param("record") TaskSms record, @Param("example") TaskSmsExample example);

    int updateByPrimaryKeySelective(TaskSms record);

    int updateByPrimaryKey(TaskSms record);
}