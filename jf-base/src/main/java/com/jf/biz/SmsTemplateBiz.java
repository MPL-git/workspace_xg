package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.SmsTemplateMapper;
import com.jf.dao.SmsTemplateExtMapper;
import com.jf.entity.SmsTemplate;
import com.jf.entity.SmsTemplateExample;
import com.jf.entity.SmsTemplateExt;
import com.jf.entity.SmsTemplateExtExample;
import com.jf.common.base.BaseService;

@Service
public class SmsTemplateBiz extends BaseService<SmsTemplate, SmsTemplateExample> {

	@Autowired
	private SmsTemplateMapper dao;
	@Autowired
	private SmsTemplateExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSmsTemplateMapper(SmsTemplateMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SmsTemplateExt save(SmsTemplateExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SmsTemplate update(SmsTemplate model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SmsTemplate model = new SmsTemplate();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SmsTemplateExt findById(int id){
		return extDao.findById(id);
	}

	public SmsTemplateExt find(SmsTemplateExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SmsTemplateExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SmsTemplateExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SmsTemplateExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SmsTemplateExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SmsTemplateExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SmsTemplateExt> list(SmsTemplateExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SmsTemplateExt> paginate(SmsTemplateExtExample example) {
		List<SmsTemplateExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
