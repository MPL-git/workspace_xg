package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAreaTempletParamExtMapper;
import com.jf.dao.ActivityAreaTempletParamMapper;
import com.jf.entity.ActivityAreaTempletParam;
import com.jf.entity.ActivityAreaTempletParamExample;
import com.jf.entity.ActivityAreaTempletParamExt;
import com.jf.entity.ActivityAreaTempletParamExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAreaTempletParamBiz extends BaseService<ActivityAreaTempletParam, ActivityAreaTempletParamExample> {

	@Autowired
	private ActivityAreaTempletParamMapper dao;
	@Autowired
	private ActivityAreaTempletParamExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAreaTempletParamMapper(ActivityAreaTempletParamMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAreaTempletParamExt save(ActivityAreaTempletParamExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityAreaTempletParam update(ActivityAreaTempletParam model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAreaTempletParam model = new ActivityAreaTempletParam();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAreaTempletParamExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAreaTempletParamExt find(ActivityAreaTempletParamExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAreaTempletParamExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAreaTempletParamExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAreaTempletParamExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAreaTempletParamExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAreaTempletParamExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAreaTempletParamExt> list(ActivityAreaTempletParamExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAreaTempletParamExt> paginate(ActivityAreaTempletParamExtExample example) {
		List<ActivityAreaTempletParamExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
