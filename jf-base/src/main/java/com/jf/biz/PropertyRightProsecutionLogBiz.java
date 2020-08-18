package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightProsecutionLogExtMapper;
import com.jf.dao.PropertyRightProsecutionLogMapper;
import com.jf.entity.PropertyRightProsecutionLog;
import com.jf.entity.PropertyRightProsecutionLogExample;
import com.jf.entity.PropertyRightProsecutionLogExt;
import com.jf.entity.PropertyRightProsecutionLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightProsecutionLogBiz extends BaseService<PropertyRightProsecutionLog, PropertyRightProsecutionLogExample> {

	@Autowired
	private PropertyRightProsecutionLogMapper dao;
	@Autowired
	private PropertyRightProsecutionLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightProsecutionLogMapper(PropertyRightProsecutionLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightProsecutionLogExt save(PropertyRightProsecutionLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightProsecutionLog update(PropertyRightProsecutionLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightProsecutionLog model = new PropertyRightProsecutionLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightProsecutionLogExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightProsecutionLogExt find(PropertyRightProsecutionLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightProsecutionLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightProsecutionLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightProsecutionLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightProsecutionLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightProsecutionLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightProsecutionLogExt> list(PropertyRightProsecutionLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightProsecutionLogExt> paginate(PropertyRightProsecutionLogExtExample example) {
		List<PropertyRightProsecutionLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
