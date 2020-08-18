package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityAreaDecorateExtMapper;
import com.jf.dao.ActivityAreaDecorateMapper;
import com.jf.entity.ActivityAreaDecorate;
import com.jf.entity.ActivityAreaDecorateExample;
import com.jf.entity.ActivityAreaDecorateExt;
import com.jf.entity.ActivityAreaDecorateExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityAreaDecorateBiz extends BaseService<ActivityAreaDecorate, ActivityAreaDecorateExample> {

	@Autowired
	private ActivityAreaDecorateMapper dao;
	@Autowired
	private ActivityAreaDecorateExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityAreaDecorateMapper(ActivityAreaDecorateMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityAreaDecorateExt save(ActivityAreaDecorateExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityAreaDecorate update(ActivityAreaDecorate model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityAreaDecorate model = new ActivityAreaDecorate();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityAreaDecorateExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityAreaDecorateExt find(ActivityAreaDecorateExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityAreaDecorateExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityAreaDecorateExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityAreaDecorateExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityAreaDecorateExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityAreaDecorateExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityAreaDecorateExt> list(ActivityAreaDecorateExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityAreaDecorateExt> paginate(ActivityAreaDecorateExtExample example) {
		List<ActivityAreaDecorateExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
