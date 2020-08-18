package com.jf.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.TaskCustom;
import com.jf.entity.TaskCustomExample;
import com.jf.entity.TaskSendMember;
@Repository
public interface TaskCustomMapper{
	public int countByExample(TaskCustomExample example);
	
	public List<TaskCustom> selectByExample(TaskCustomExample TaskCustomExample);

	public int batchInsertTaskSendMemberByTaskId(@Param("itemsList")List<Integer> itemsList,@Param("taskSendMember")TaskSendMember taskSendMember);

	public List<TaskCustom> detailAuditSelectByExample(TaskCustomExample TaskCustomExample);
	
	
	
}