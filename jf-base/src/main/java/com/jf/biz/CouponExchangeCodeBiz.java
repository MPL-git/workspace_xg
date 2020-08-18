package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CouponExchangeCodeExtMapper;
import com.jf.dao.CouponExchangeCodeMapper;
import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeExample;
import com.jf.entity.CouponExchangeCodeExt;
import com.jf.entity.CouponExchangeCodeExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponExchangeCodeBiz extends BaseService<CouponExchangeCode, CouponExchangeCodeExample> {

	@Autowired
	private CouponExchangeCodeMapper dao;
	@Autowired
	private CouponExchangeCodeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCouponExchangeCodeMapper(CouponExchangeCodeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CouponExchangeCodeExt save(CouponExchangeCodeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CouponExchangeCode update(CouponExchangeCode model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CouponExchangeCode model = new CouponExchangeCode();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CouponExchangeCodeExt findById(int id){
		return extDao.findById(id);
	}

	public CouponExchangeCodeExt find(CouponExchangeCodeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CouponExchangeCodeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CouponExchangeCodeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CouponExchangeCodeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CouponExchangeCodeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CouponExchangeCodeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CouponExchangeCodeExt> list(CouponExchangeCodeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CouponExchangeCodeExt> paginate(CouponExchangeCodeExtExample example) {
		List<CouponExchangeCodeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
