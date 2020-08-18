package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TaskSmsExtMapper;
import com.jf.dao.TaskSmsMapper;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsExample;
import com.jf.entity.TaskSmsExt;
import com.jf.entity.TaskSmsExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskSmsBiz extends BaseService<TaskSms, TaskSmsExample> {

	@Autowired
	private TaskSmsMapper dao;
	@Autowired
	private TaskSmsExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaskSmsMapper(TaskSmsMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaskSmsExt save(TaskSmsExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaskSms update(TaskSms model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaskSms model = new TaskSms();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaskSmsExt findById(int id){
		return extDao.findById(id);
	}

	public TaskSmsExt find(TaskSmsExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaskSmsExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaskSmsExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaskSmsExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaskSmsExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaskSmsExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaskSmsExt> list(TaskSmsExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaskSmsExt> paginate(TaskSmsExtExample example) {
		List<TaskSmsExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
