package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityProductAuditLogExtMapper;
import com.jf.dao.ActivityProductAuditLogMapper;
import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductAuditLogExample;
import com.jf.entity.ActivityProductAuditLogExt;
import com.jf.entity.ActivityProductAuditLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityProductAuditLogBiz extends BaseService<ActivityProductAuditLog, ActivityProductAuditLogExample> {

	@Autowired
	private ActivityProductAuditLogMapper dao;
	@Autowired
	private ActivityProductAuditLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityProductAuditLogMapper(ActivityProductAuditLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityProductAuditLogExt save(ActivityProductAuditLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityProductAuditLog update(ActivityProductAuditLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityProductAuditLog model = new ActivityProductAuditLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityProductAuditLogExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityProductAuditLogExt find(ActivityProductAuditLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityProductAuditLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityProductAuditLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityProductAuditLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityProductAuditLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityProductAuditLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityProductAuditLogExt> list(ActivityProductAuditLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityProductAuditLogExt> paginate(ActivityProductAuditLogExtExample example) {
		List<ActivityProductAuditLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
