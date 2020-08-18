package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberExample;

@Repository
public interface TaskSendMemberMapper extends BaseDao<TaskSendMember, TaskSendMemberExample> {
    int countByExample(TaskSendMemberExample example);

    int deleteByExample(TaskSendMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskSendMember record);

    int insertSelective(TaskSendMember record);

    List<TaskSendMember> selectByExample(TaskSendMemberExample example);

    TaskSendMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberExample example);

    int updateByExample(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberExample example);

    int updateByPrimaryKeySelective(TaskSendMember record);

    int updateByPrimaryKey(TaskSendMember record);
}