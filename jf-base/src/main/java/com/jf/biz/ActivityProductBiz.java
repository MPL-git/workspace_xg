package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityProductExtMapper;
import com.jf.dao.ActivityProductMapper;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;
import com.jf.entity.ActivityProductExt;
import com.jf.entity.ActivityProductExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityProductBiz extends BaseService<ActivityProduct, ActivityProductExample> {

	@Autowired
	private ActivityProductMapper dao;
	@Autowired
	private ActivityProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityProductMapper(ActivityProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityProductExt save(ActivityProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityProduct update(ActivityProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityProduct model = new ActivityProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityProductExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityProductExt find(ActivityProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityProductExt> list(ActivityProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityProductExt> paginate(ActivityProductExtExample example) {
		List<ActivityProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
