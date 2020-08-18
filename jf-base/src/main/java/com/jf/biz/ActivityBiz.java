package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityExtMapper;
import com.jf.dao.ActivityMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityExt;
import com.jf.entity.ActivityExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityBiz extends BaseService<Activity, ActivityExample> {

	@Autowired
	private ActivityMapper dao;
	@Autowired
	private ActivityExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityMapper(ActivityMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityExt save(ActivityExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Activity update(Activity model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Activity model = new Activity();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityExt find(ActivityExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityExt> list(ActivityExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityExt> paginate(ActivityExtExample example) {
		List<ActivityExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
