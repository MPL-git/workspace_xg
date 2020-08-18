package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityBrandGroupActivityExtMapper;
import com.jf.dao.ActivityBrandGroupActivityMapper;
import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupActivityExample;
import com.jf.entity.ActivityBrandGroupActivityExt;
import com.jf.entity.ActivityBrandGroupActivityExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityBrandGroupActivityBiz extends BaseService<ActivityBrandGroupActivity, ActivityBrandGroupActivityExample> {

	@Autowired
	private ActivityBrandGroupActivityMapper dao;
	@Autowired
	private ActivityBrandGroupActivityExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityBrandGroupActivityMapper(ActivityBrandGroupActivityMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityBrandGroupActivityExt save(ActivityBrandGroupActivityExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityBrandGroupActivity update(ActivityBrandGroupActivity model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityBrandGroupActivity model = new ActivityBrandGroupActivity();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityBrandGroupActivityExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityBrandGroupActivityExt find(ActivityBrandGroupActivityExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityBrandGroupActivityExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityBrandGroupActivityExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityBrandGroupActivityExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityBrandGroupActivityExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityBrandGroupActivityExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityBrandGroupActivityExt> list(ActivityBrandGroupActivityExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityBrandGroupActivityExt> paginate(ActivityBrandGroupActivityExtExample example) {
		List<ActivityBrandGroupActivityExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
