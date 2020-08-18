package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ActivityProductDepositExtMapper;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;
import com.jf.entity.ActivityProductDepositExt;
import com.jf.entity.ActivityProductDepositExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityProductDepositBiz extends BaseService<ActivityProductDeposit, ActivityProductDepositExample> {

	@Autowired
	private ActivityProductDepositMapper dao;
	@Autowired
	private ActivityProductDepositExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setActivityProductDepositMapper(ActivityProductDepositMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ActivityProductDepositExt save(ActivityProductDepositExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ActivityProductDeposit update(ActivityProductDeposit model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ActivityProductDeposit model = new ActivityProductDeposit();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ActivityProductDepositExt findById(int id){
		return extDao.findById(id);
	}

	public ActivityProductDepositExt find(ActivityProductDepositExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ActivityProductDepositExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ActivityProductDepositExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ActivityProductDepositExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ActivityProductDepositExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ActivityProductDepositExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ActivityProductDepositExt> list(ActivityProductDepositExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ActivityProductDepositExt> paginate(ActivityProductDepositExtExample example) {
		List<ActivityProductDepositExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
