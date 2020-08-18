package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SingleProductActivityCnfExtMapper;
import com.jf.dao.SingleProductActivityCnfMapper;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;
import com.jf.entity.SingleProductActivityCnfExt;
import com.jf.entity.SingleProductActivityCnfExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SingleProductActivityCnfBiz extends BaseService<SingleProductActivityCnf, SingleProductActivityCnfExample> {

	@Autowired
	private SingleProductActivityCnfMapper dao;
	@Autowired
	private SingleProductActivityCnfExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSingleProductActivityCnfMapper(SingleProductActivityCnfMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SingleProductActivityCnfExt save(SingleProductActivityCnfExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SingleProductActivityCnf update(SingleProductActivityCnf model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SingleProductActivityCnf model = new SingleProductActivityCnf();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SingleProductActivityCnfExt findById(int id){
		return extDao.findById(id);
	}

	public SingleProductActivityCnfExt find(SingleProductActivityCnfExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SingleProductActivityCnfExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SingleProductActivityCnfExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SingleProductActivityCnfExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SingleProductActivityCnfExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SingleProductActivityCnfExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SingleProductActivityCnfExt> list(SingleProductActivityCnfExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SingleProductActivityCnfExt> paginate(SingleProductActivityCnfExtExample example) {
		List<SingleProductActivityCnfExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
