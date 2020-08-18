package com.jf.dao;

import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskLogMapper extends BaseDao<TaskLog, TaskLogExample> {
    int countByExample(TaskLogExample example);

    int deleteByExample(TaskLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    List<TaskLog> selectByExample(TaskLogExample example);

    TaskLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByExample(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);
}