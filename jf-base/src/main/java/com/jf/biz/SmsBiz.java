package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SmsMapper;
import com.jf.dao.SmsExtMapper;
import com.jf.entity.Sms;
import com.jf.entity.SmsExample;
import com.jf.entity.SmsExt;
import com.jf.entity.SmsExtExample;
import com.jf.common.base.BaseService;

@Service
public class SmsBiz extends BaseService<Sms, SmsExample> {

	@Autowired
	private SmsMapper dao;
	@Autowired
	private SmsExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSmsMapper(SmsMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SmsExt save(SmsExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Sms update(Sms model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Sms model = new Sms();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SmsExt findById(int id){
		return extDao.findById(id);
	}

	public SmsExt find(SmsExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SmsExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SmsExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SmsExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SmsExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SmsExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SmsExt> list(SmsExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SmsExt> paginate(SmsExtExample example) {
		List<SmsExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
