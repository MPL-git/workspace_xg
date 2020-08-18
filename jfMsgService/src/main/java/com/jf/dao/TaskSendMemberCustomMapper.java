package com.jf.dao;

import com.jf.entity.Task;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberCustom;
import com.jf.entity.TaskSendMemberCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskSendMemberCustomMapper {
    
	public int countByCustomExample(TaskSendMemberCustomExample example);

	public List<TaskSendMemberCustom> selectByCustomExample(TaskSendMemberCustomExample example);

	public TaskSendMemberCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberCustomExample example);

	public List<Map<String, Object>> sendSmsXwSendList(Map<String, Object> paramMap);

	public List<Map<String, Object>> sendSmsXYSendList(Map<String, Object> paramMap);

	public List<Map<String, Object>> sendSmsSendListByChannel(Map<String, Object> paramMap);

	public int updateTaskSendMemberList(Map<String, Object> paramMap);
	
	public int updateTaskSendMember(Map<String, Object> paramMap);

	public int xyNotifyTaskSendMember(Map<String, Object> paramMap);

	public List<Task> sendTaskList(Map<String, Object> paramMap);

	public List<Map<String, Object>> sendSmsXwTaskList();

	public List<Map<String, Object>> sendSmsXYTaskList();

	public List<Map<String, Object>> sendSmsTaskListByChannel(@Param("smsChannel") String smsChannel);

	public void updateTaskStatus(@Param("taskId")Integer taskId,@Param("status")String status);
}