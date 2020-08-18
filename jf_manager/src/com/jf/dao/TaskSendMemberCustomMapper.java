package com.jf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberCustom;
import com.jf.entity.TaskSendMemberCustomExample;

@Repository
public interface TaskSendMemberCustomMapper {
    
    public int countByCustomExample(TaskSendMemberCustomExample example);

    public List<TaskSendMemberCustom> selectByCustomExample(TaskSendMemberCustomExample example);

    public TaskSendMemberCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberCustomExample example);

    public int addTaskSendMember(@Param("sendType")String sendType, @Param("sendValueSet")Set<String> sendValueSet, @Param("taskSendMember")TaskSendMember taskSendMember);
    
    public int getTaskMemberLoginCount(Map<String, Object> paramMap);
    
    public int getTaskMemberOrderCount(Map<String, Object> paramMap);
    
    public int getTaskCombineOrderCount(Map<String, Object> paramMap);
    
    public Date getMemberLoginDate(Map<String, Object> paramMap);
    
    public int getCombineOrderCount(Map<String, Object> paramMap);
    
    
}