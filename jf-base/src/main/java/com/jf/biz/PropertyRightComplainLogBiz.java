package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightComplainLogExtMapper;
import com.jf.dao.PropertyRightComplainLogMapper;
import com.jf.entity.PropertyRightComplainLog;
import com.jf.entity.PropertyRightComplainLogExample;
import com.jf.entity.PropertyRightComplainLogExt;
import com.jf.entity.PropertyRightComplainLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightComplainLogBiz extends BaseService<PropertyRightComplainLog, PropertyRightComplainLogExample> {

	@Autowired
	private PropertyRightComplainLogMapper dao;
	@Autowired
	private PropertyRightComplainLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightComplainLogMapper(PropertyRightComplainLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightComplainLogExt save(PropertyRightComplainLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightComplainLog update(PropertyRightComplainLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightComplainLog model = new PropertyRightComplainLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightComplainLogExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightComplainLogExt find(PropertyRightComplainLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightComplainLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightComplainLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightComplainLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightComplainLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightComplainLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightComplainLogExt> list(PropertyRightComplainLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightComplainLogExt> paginate(PropertyRightComplainLogExtExample example) {
		List<PropertyRightComplainLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
