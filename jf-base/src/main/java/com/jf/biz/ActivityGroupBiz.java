package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityGroupExtMapper;
import com.jf.dao.ActivityGroupMapper;
import com.jf.entity.ActivityGroup;
import com.jf.entity.ActivityGroupExample;
import com.jf.entity.ActivityGroupExt;
import com.jf.entity.ActivityGroupExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityGroupBiz extends BaseService<ActivityGroup, ActivityGroupExample> {

	@Autowired
	private ActivityGroupMapper dao;
	@Autowired
	private ActivityGroupExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityGroupMapper(ActivityGroupMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityGroupExt save(ActivityGroupExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityGroup update(ActivityGroup model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityGroup model = new ActivityGroup();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityGroupExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityGroupExt find(ActivityGroupExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityGroupExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityGroupExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityGroupExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityGroupExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityGroupExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityGroupExt> list(ActivityGroupExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityGroupExt> paginate(ActivityGroupExtExample example) {
		List<ActivityGroupExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
