package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FreightTemplateExtMapper;
import com.jf.dao.FreightTemplateMapper;
import com.jf.entity.FreightTemplate;
import com.jf.entity.FreightTemplateExample;
import com.jf.entity.FreightTemplateExt;
import com.jf.entity.FreightTemplateExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FreightTemplateBiz extends BaseService<FreightTemplate, FreightTemplateExample> {

	@Autowired
	private FreightTemplateMapper dao;
	@Autowired
	private FreightTemplateExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setFreightTemplateMapper(FreightTemplateMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public FreightTemplateExt save(FreightTemplateExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public FreightTemplate update(FreightTemplate model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FreightTemplate model = new FreightTemplate();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public FreightTemplateExt findById(int id){
		return extDao.findById(id);
	}

	public FreightTemplateExt find(FreightTemplateExtExample example){
		return extDao.find(example.fill());
	}

	public int count(FreightTemplateExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, FreightTemplateExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, FreightTemplateExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, FreightTemplateExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(FreightTemplateExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<FreightTemplateExt> list(FreightTemplateExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<FreightTemplateExt> paginate(FreightTemplateExtExample example) {
		List<FreightTemplateExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
