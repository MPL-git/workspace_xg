package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.TaskSendMemberMapper;
import com.jf.dao.TaskSendMemberExtMapper;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberExample;
import com.jf.entity.TaskSendMemberExt;
import com.jf.entity.TaskSendMemberExtExample;
import com.jf.common.base.BaseService;

@Service
public class TaskSendMemberBiz extends BaseService<TaskSendMember, TaskSendMemberExample> {

	@Autowired
	private TaskSendMemberMapper dao;
	@Autowired
	private TaskSendMemberExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaskSendMemberMapper(TaskSendMemberMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaskSendMemberExt save(TaskSendMemberExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaskSendMember update(TaskSendMember model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaskSendMember model = new TaskSendMember();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaskSendMemberExt findById(int id){
		return extDao.findById(id);
	}

	public TaskSendMemberExt find(TaskSendMemberExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaskSendMemberExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaskSendMemberExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaskSendMemberExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaskSendMemberExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaskSendMemberExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaskSendMemberExt> list(TaskSendMemberExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaskSendMemberExt> paginate(TaskSendMemberExtExample example) {
		List<TaskSendMemberExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
