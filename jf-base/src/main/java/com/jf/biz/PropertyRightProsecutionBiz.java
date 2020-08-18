package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightProsecutionExtMapper;
import com.jf.dao.PropertyRightProsecutionMapper;
import com.jf.entity.PropertyRightProsecution;
import com.jf.entity.PropertyRightProsecutionExample;
import com.jf.entity.PropertyRightProsecutionExt;
import com.jf.entity.PropertyRightProsecutionExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightProsecutionBiz extends BaseService<PropertyRightProsecution, PropertyRightProsecutionExample> {

	@Autowired
	private PropertyRightProsecutionMapper dao;
	@Autowired
	private PropertyRightProsecutionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightProsecutionMapper(PropertyRightProsecutionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightProsecutionExt save(PropertyRightProsecutionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightProsecution update(PropertyRightProsecution model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightProsecution model = new PropertyRightProsecution();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightProsecutionExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightProsecutionExt find(PropertyRightProsecutionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightProsecutionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightProsecutionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightProsecutionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightProsecutionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightProsecutionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightProsecutionExt> list(PropertyRightProsecutionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightProsecutionExt> paginate(PropertyRightProsecutionExtExample example) {
		List<PropertyRightProsecutionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
