package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAuthExtMapper;
import com.jf.dao.ActivityAuthMapper;
import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityAuthExample;
import com.jf.entity.ActivityAuthExt;
import com.jf.entity.ActivityAuthExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAuthBiz extends BaseService<ActivityAuth, ActivityAuthExample> {

	@Autowired
	private ActivityAuthMapper dao;
	@Autowired
	private ActivityAuthExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAuthMapper(ActivityAuthMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAuthExt save(ActivityAuthExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityAuth update(ActivityAuth model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAuth model = new ActivityAuth();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAuthExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAuthExt find(ActivityAuthExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAuthExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAuthExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAuthExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAuthExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAuthExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAuthExt> list(ActivityAuthExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAuthExt> paginate(ActivityAuthExtExample example) {
		List<ActivityAuthExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
