package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SmsBlackMobileMapper;
import com.jf.dao.SmsBlackMobileExtMapper;
import com.jf.entity.SmsBlackMobile;
import com.jf.entity.SmsBlackMobileExample;
import com.jf.entity.SmsBlackMobileExt;
import com.jf.entity.SmsBlackMobileExtExample;
import com.jf.common.base.BaseService;

@Service
public class SmsBlackMobileBiz extends BaseService<SmsBlackMobile, SmsBlackMobileExample> {

	@Autowired
	private SmsBlackMobileMapper dao;
	@Autowired
	private SmsBlackMobileExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSmsBlackMobileMapper(SmsBlackMobileMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SmsBlackMobileExt save(SmsBlackMobileExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SmsBlackMobile update(SmsBlackMobile model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SmsBlackMobile model = new SmsBlackMobile();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SmsBlackMobileExt findById(int id){
		return extDao.findById(id);
	}

	public SmsBlackMobileExt find(SmsBlackMobileExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SmsBlackMobileExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SmsBlackMobileExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SmsBlackMobileExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SmsBlackMobileExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SmsBlackMobileExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SmsBlackMobileExt> list(SmsBlackMobileExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SmsBlackMobileExt> paginate(SmsBlackMobileExtExample example) {
		List<SmsBlackMobileExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
