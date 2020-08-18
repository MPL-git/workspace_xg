package com.jf.service;

import com.jf.biz.TaskActivitySelectionBiz;
import com.jf.biz.TaskBiz;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.TaskActivitySelectionExt;
import com.jf.entity.TaskExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskActivitySelectionService extends TaskActivitySelectionBiz {

	@Autowired
	private TaskBiz taskBiz;
	@Autowired
	private DecorateInfoService decorateInfoService;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------

	public Map<String, Object> findDetailById(int id) {
		TaskActivitySelectionExt model = findById(id);
		if(model == null || model.isDeleted()){
			throw new BizException("找不到ID为[" + id + "]的精选活动");
		}

		TaskExt task = taskBiz.findById(model.getTaskId());
		if(task == null || task.isDeleted() || !task.isNormal()){
			throw new BizException("找不到ID为[" + model.getTaskId() + "]的精选活动任务");
		}

		Map<String, Object> map = new HashMap<>();
		map.put("title", task.getName());
		map.put("taskExplain", task.getTaskExplain());
		map.put("content", task.getContent());
		map.put("pic", model.getCoverPic());
		map.put("decorateInfoId", model.getDecorateInfoId());

		if(model.getDecorateInfoId() != null){
			Map<String,Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(model.getDecorateInfoId(),null,null);
			map.put("decorateInfo", decorateInfoMap);
		}

		return map;
	}






}
