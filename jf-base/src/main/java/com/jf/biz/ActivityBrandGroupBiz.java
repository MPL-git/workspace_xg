package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityBrandGroupExtMapper;
import com.jf.dao.ActivityBrandGroupMapper;
import com.jf.entity.ActivityBrandGroup;
import com.jf.entity.ActivityBrandGroupExample;
import com.jf.entity.ActivityBrandGroupExt;
import com.jf.entity.ActivityBrandGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityBrandGroupBiz extends BaseService<ActivityBrandGroup, ActivityBrandGroupExample> {

	@Autowired
	private ActivityBrandGroupMapper dao;
	@Autowired
	private ActivityBrandGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityBrandGroupMapper(ActivityBrandGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityBrandGroupExt save(ActivityBrandGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityBrandGroup update(ActivityBrandGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityBrandGroup model = new ActivityBrandGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityBrandGroupExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityBrandGroupExt find(ActivityBrandGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityBrandGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityBrandGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityBrandGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityBrandGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityBrandGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityBrandGroupExt> list(ActivityBrandGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityBrandGroupExt> paginate(ActivityBrandGroupExtExample example) {
		List<ActivityBrandGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
