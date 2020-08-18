package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityRuleConfigurationExtMapper;
import com.jf.dao.ActivityRuleConfigurationMapper;
import com.jf.entity.ActivityRuleConfiguration;
import com.jf.entity.ActivityRuleConfigurationExample;
import com.jf.entity.ActivityRuleConfigurationExt;
import com.jf.entity.ActivityRuleConfigurationExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityRuleConfigurationBiz extends BaseService<ActivityRuleConfiguration, ActivityRuleConfigurationExample> {

	@Autowired
	private ActivityRuleConfigurationMapper dao;
	@Autowired
	private ActivityRuleConfigurationExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityRuleConfigurationMapper(ActivityRuleConfigurationMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityRuleConfigurationExt save(ActivityRuleConfigurationExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityRuleConfiguration update(ActivityRuleConfiguration model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityRuleConfiguration model = new ActivityRuleConfiguration();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityRuleConfigurationExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityRuleConfigurationExt find(ActivityRuleConfigurationExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityRuleConfigurationExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityRuleConfigurationExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityRuleConfigurationExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityRuleConfigurationExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityRuleConfigurationExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityRuleConfigurationExt> list(ActivityRuleConfigurationExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityRuleConfigurationExt> paginate(ActivityRuleConfigurationExtExample example) {
		List<ActivityRuleConfigurationExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
