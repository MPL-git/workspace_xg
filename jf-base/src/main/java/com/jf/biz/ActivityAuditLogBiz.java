package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAuditLogExtMapper;
import com.jf.dao.ActivityAuditLogMapper;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogExample;
import com.jf.entity.ActivityAuditLogExt;
import com.jf.entity.ActivityAuditLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAuditLogBiz extends BaseService<ActivityAuditLog, ActivityAuditLogExample> {

	@Autowired
	private ActivityAuditLogMapper dao;
	@Autowired
	private ActivityAuditLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAuditLogMapper(ActivityAuditLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAuditLogExt save(ActivityAuditLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityAuditLog update(ActivityAuditLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAuditLog model = new ActivityAuditLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAuditLogExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAuditLogExt find(ActivityAuditLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAuditLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAuditLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAuditLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAuditLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAuditLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAuditLogExt> list(ActivityAuditLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAuditLogExt> paginate(ActivityAuditLogExtExample example) {
		List<ActivityAuditLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
