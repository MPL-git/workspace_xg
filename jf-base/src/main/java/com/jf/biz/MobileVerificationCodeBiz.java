package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.MobileVerificationCodeMapper;
import com.jf.dao.MobileVerificationCodeExtMapper;
import com.jf.entity.MobileVerificationCode;
import com.jf.entity.MobileVerificationCodeExample;
import com.jf.entity.MobileVerificationCodeExt;
import com.jf.entity.MobileVerificationCodeExtExample;
import com.jf.common.base.BaseService;

@Service
public class MobileVerificationCodeBiz extends BaseService<MobileVerificationCode, MobileVerificationCodeExample> {

	@Autowired
	private MobileVerificationCodeMapper dao;
	@Autowired
	private MobileVerificationCodeExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMobileVerificationCodeMapper(MobileVerificationCodeMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MobileVerificationCodeExt save(MobileVerificationCodeExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MobileVerificationCode update(MobileVerificationCode model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MobileVerificationCode model = new MobileVerificationCode();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MobileVerificationCodeExt findById(int id){
		return extDao.findById(id);
	}

	public MobileVerificationCodeExt find(MobileVerificationCodeExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MobileVerificationCodeExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MobileVerificationCodeExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MobileVerificationCodeExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MobileVerificationCodeExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MobileVerificationCodeExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MobileVerificationCodeExt> list(MobileVerificationCodeExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MobileVerificationCodeExt> paginate(MobileVerificationCodeExtExample example) {
		List<MobileVerificationCodeExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
