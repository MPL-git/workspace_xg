package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleActivityExtMapper;
import com.jf.dao.SingleActivityMapper;
import com.jf.entity.SingleActivity;
import com.jf.entity.SingleActivityExample;
import com.jf.entity.SingleActivityExt;
import com.jf.entity.SingleActivityExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleActivityBiz extends BaseService<SingleActivity, SingleActivityExample> {

	@Autowired
	private SingleActivityMapper dao;
	@Autowired
	private SingleActivityExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleActivityMapper(SingleActivityMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleActivityExt save(SingleActivityExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleActivity update(SingleActivity model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleActivity model = new SingleActivity();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleActivityExt findById(int id){
		return extDao.findById(id);
	}

	public SingleActivityExt find(SingleActivityExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleActivityExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleActivityExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleActivityExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleActivityExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleActivityExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleActivityExt> list(SingleActivityExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleActivityExt> paginate(SingleActivityExtExample example) {
		List<SingleActivityExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
