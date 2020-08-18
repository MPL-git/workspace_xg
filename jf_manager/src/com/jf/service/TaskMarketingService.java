package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.dao.TaskMapper;
import com.jf.dao.TaskMarketingCustomMapper;
import com.jf.dao.TaskMarketingMapper;
import com.jf.dao.TaskSendMemberCustomMapper;
import com.jf.entity.Task;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingCustom;
import com.jf.entity.TaskMarketingCustomExample;
import com.jf.entity.TaskMarketingExample;

@Service
@Transactional
public class TaskMarketingService extends BaseService<TaskMarketing, TaskMarketingExample> {

	@Autowired
	private TaskMarketingMapper taskMarketingMapper;
	
	@Autowired
	private TaskMarketingCustomMapper taskMarketingCustomMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private TaskSendMemberCustomMapper taskSendMemberCustomMapper;
	
	@Autowired
	public void setTaskMarketingMapper(TaskMarketingMapper taskMarketingMapper) {
		super.setDao(taskMarketingMapper);
		this.taskMarketingMapper = taskMarketingMapper;
	}
	
	public int countByCustomExample(TaskMarketingCustomExample example) {
		return taskMarketingCustomMapper.countByCustomExample(example);
	}

	public List<TaskMarketingCustom> selectByCustomExample(TaskMarketingCustomExample example) {
		List<TaskMarketingCustom> taskMarketingCustomList = taskMarketingCustomMapper.selectByCustomExample(example);
		for(TaskMarketingCustom taskMarketingCustom : taskMarketingCustomList) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sendType", taskMarketingCustom.getTaskSendType());
			paramMap.put("taskId", taskMarketingCustom.getTaskId());
			taskMarketingCustom.setMemberLoginCount(taskSendMemberCustomMapper.getTaskMemberLoginCount(paramMap));
			taskMarketingCustom.setMemberOrderCount(taskSendMemberCustomMapper.getTaskMemberOrderCount(paramMap));
			taskMarketingCustom.setCombineOrdeCount(taskSendMemberCustomMapper.getTaskCombineOrderCount(paramMap));
		}
		return taskMarketingCustomList;
	}

	public TaskMarketingCustom selectByCustomPrimaryKey(Integer id) {
		return taskMarketingCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") TaskMarketing record, @Param("example") TaskMarketingCustomExample example) {
		return taskMarketingCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public void eidtTaskSms(TaskMarketing taskMarketing, Task task) {
		if(taskMarketing.getId() == null ) {
			task.setType(Const.TASK_TYPE_1);
//			task.setTimeInterval(Const.TASK_TIME_INTERVAL);
			task.setTimeInterval(6);
			task.setSendCount(Const.TASK_SEND_COUNT);
			task.setStatus(Const.TASK_STATUS_0);
			task.setCreateBy(taskMarketing.getCreateBy());
			task.setCreateDate(taskMarketing.getCreateDate());
			task.setDelFlag("0");
			taskMapper.insertSelective(task);
			taskMarketing.setTaskId(task.getId());
			taskMarketing.setDelFlag("0");
			taskMarketingMapper.insertSelective(taskMarketing);
		}else {
			TaskMarketing tm = taskMarketingMapper.selectByPrimaryKey(taskMarketing.getId());
			taskMarketingMapper.updateByPrimaryKeySelective(taskMarketing);
			Task tk = taskMapper.selectByPrimaryKey(tm.getTaskId());
			task.setId(tk.getId());
			task.setType(tk.getType());
			task.setTimeInterval(tk.getTimeInterval());
			task.setSendCount(tk.getSendCount());
			task.setStatus(Const.TASK_STATUS_0);
			task.setCreateBy(tk.getCreateBy());
			task.setCreateDate(tk.getCreateDate());
			task.setUpdateBy(taskMarketing.getUpdateBy());
			task.setUpdateDate(taskMarketing.getUpdateDate());
			task.setRemarks(tk.getRemarks());
			task.setDelFlag(tk.getDelFlag());
			taskMapper.updateByPrimaryKeyWithBLOBs(task);
		}
	}
	
	
	
}
