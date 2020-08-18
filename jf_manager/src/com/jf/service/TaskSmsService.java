package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.dao.TaskLogMapper;
import com.jf.dao.TaskMapper;
import com.jf.dao.TaskSendMemberCustomMapper;
import com.jf.dao.TaskSmsCustomMapper;
import com.jf.dao.TaskSmsMapper;
import com.jf.entity.Task;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsCustom;
import com.jf.entity.TaskSmsCustomExample;
import com.jf.entity.TaskSmsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TaskSmsService extends BaseService<TaskSms, TaskSmsExample> {

	@Autowired
	private TaskSmsMapper taskSmsMapper;
	
	@Autowired
	private TaskSmsCustomMapper taskSmsCustomMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private TaskLogMapper taskLogMapper;
	
	@Autowired
	private TaskSendMemberCustomMapper taskSendMemberCustomMapper;
	
	@Autowired
	public void setTaskSmsMapper(TaskSmsMapper taskSmsMapper) {
		super.setDao(taskSmsMapper);
		this.taskSmsMapper = taskSmsMapper;
	}
	
	public int countByCustomExample(TaskSmsCustomExample example) {
		return taskSmsCustomMapper.countByCustomExample(example);
	}

	public List<TaskSmsCustom> selectByCustomExample(TaskSmsCustomExample example) {
		return taskSmsCustomMapper.selectByCustomExample(example);
	}

	public TaskSmsCustom selectByCustomPrimaryKey(Integer id) {
		return taskSmsCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") TaskSms record, @Param("example") TaskSmsCustomExample example) {
		return taskSmsCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	public void eidtTaskSms(TaskSms taskSms, Task task) {
		if(taskSms.getId() == null ) {
			task.setType(Const.TASK_TYPE_0);
//			task.setTimeInterval(Const.TASK_TIME_INTERVAL);
			task.setTimeInterval(6);
			task.setStatus(Const.TASK_STATUS_0);
			task.setCreateBy(taskSms.getCreateBy());
			task.setCreateDate(taskSms.getCreateDate());
			if(task.getSendDate() == null ) {
				task.setSendDate(DateUtil.getYesterDayDate());
			}
			task.setLastSendDate(DateUtil.getYesterDayDate());
			task.setDelFlag("0");
			taskMapper.insertSelective(task);
			taskSms.setTaskId(task.getId());
			taskSms.setDelFlag("0");
			taskSmsMapper.insertSelective(taskSms);
		}else {
			TaskSms ts = taskSmsMapper.selectByPrimaryKey(taskSms.getId());
			taskSmsMapper.updateByPrimaryKeySelective(taskSms);
			Task tk = taskMapper.selectByPrimaryKey(ts.getTaskId());
			task.setId(tk.getId());
			task.setType(tk.getType());
			task.setTimeInterval(tk.getTimeInterval());
			task.setStatus(Const.TASK_STATUS_0);
			if(task.getSendDate() == null ) {
				task.setSendDate(DateUtil.getYesterDayDate());
			}
			task.setLastSendDate(tk.getLastSendDate());
			task.setCreateBy(tk.getCreateBy());
			task.setCreateDate(tk.getCreateDate());
			task.setUpdateBy(taskSms.getUpdateBy());
			task.setUpdateDate(taskSms.getUpdateDate());
			task.setRemarks(tk.getRemarks());
			task.setDelFlag(tk.getDelFlag());
			taskMapper.updateByPrimaryKeyWithBLOBs(task);
		}
	}

	public Integer getDetailedInformation(Map<String, Object> paramMap, String type) {
		if ("1".equals(type)) {//7天内登录用户数
			return taskSendMemberCustomMapper.getTaskMemberLoginCount(paramMap);
		}else if ("2".equals(type)) {//7天内付款用户数
			return taskSendMemberCustomMapper.getTaskMemberOrderCount(paramMap);
		}else if ("3".equals(type)){//7天内付款订单数
			return taskSendMemberCustomMapper.getTaskCombineOrderCount(paramMap);
		}
		return null;
	}
}
