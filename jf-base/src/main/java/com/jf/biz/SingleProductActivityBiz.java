package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleProductActivityExtMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.SingleProductActivityExt;
import com.jf.entity.SingleProductActivityExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleProductActivityBiz extends BaseService<SingleProductActivity, SingleProductActivityExample> {

	@Autowired
	private SingleProductActivityMapper dao;
	@Autowired
	private SingleProductActivityExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleProductActivityExt save(SingleProductActivityExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleProductActivity update(SingleProductActivity model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleProductActivity model = new SingleProductActivity();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleProductActivityExt findById(int id){
		return extDao.findById(id);
	}

	public SingleProductActivityExt find(SingleProductActivityExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleProductActivityExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleProductActivityExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleProductActivityExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleProductActivityExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleProductActivityExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleProductActivityExt> list(SingleProductActivityExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleProductActivityExt> paginate(SingleProductActivityExtExample example) {
		List<SingleProductActivityExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
