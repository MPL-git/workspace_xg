package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAreaExtMapper;
import com.jf.dao.ActivityAreaMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityAreaExt;
import com.jf.entity.ActivityAreaExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAreaBiz extends BaseService<ActivityArea, ActivityAreaExample> {

	@Autowired
	private ActivityAreaMapper dao;
	@Autowired
	private ActivityAreaExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAreaMapper(ActivityAreaMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAreaExt save(ActivityAreaExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityArea update(ActivityArea model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityArea model = new ActivityArea();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAreaExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAreaExt find(ActivityAreaExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAreaExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAreaExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAreaExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAreaExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAreaExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAreaExt> list(ActivityAreaExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAreaExt> paginate(ActivityAreaExtExample example) {
		List<ActivityAreaExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
