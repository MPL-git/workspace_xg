package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.TaskLogMapper;
import com.jf.dao.TaskLogExtMapper;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskLogExample;
import com.jf.entity.TaskLogExt;
import com.jf.entity.TaskLogExtExample;
import com.jf.common.base.BaseService;

@Service
public class TaskLogBiz extends BaseService<TaskLog, TaskLogExample> {

	@Autowired
	private TaskLogMapper dao;
	@Autowired
	private TaskLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaskLogMapper(TaskLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaskLogExt save(TaskLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaskLog update(TaskLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaskLog model = new TaskLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaskLogExt findById(int id){
		return extDao.findById(id);
	}

	public TaskLogExt find(TaskLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaskLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaskLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaskLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaskLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaskLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaskLogExt> list(TaskLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaskLogExt> paginate(TaskLogExtExample example) {
		List<TaskLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
