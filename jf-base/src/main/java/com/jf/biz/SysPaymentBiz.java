package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SysPaymentMapper;
import com.jf.dao.SysPaymentExtMapper;
import com.jf.entity.SysPayment;
import com.jf.entity.SysPaymentExample;
import com.jf.entity.SysPaymentExt;
import com.jf.entity.SysPaymentExtExample;
import com.jf.common.base.BaseService;

@Service
public class SysPaymentBiz extends BaseService<SysPayment, SysPaymentExample> {

	@Autowired
	private SysPaymentMapper dao;
	@Autowired
	private SysPaymentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysPaymentMapper(SysPaymentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysPaymentExt save(SysPaymentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SysPayment update(SysPayment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SysPayment model = new SysPayment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SysPaymentExt findById(int id){
		return extDao.findById(id);
	}

	public SysPaymentExt find(SysPaymentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysPaymentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysPaymentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysPaymentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysPaymentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysPaymentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysPaymentExt> list(SysPaymentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysPaymentExt> paginate(SysPaymentExtExample example) {
		List<SysPaymentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
