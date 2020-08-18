package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightAppealLogExtMapper;
import com.jf.dao.PropertyRightAppealLogMapper;
import com.jf.entity.PropertyRightAppealLog;
import com.jf.entity.PropertyRightAppealLogExample;
import com.jf.entity.PropertyRightAppealLogExt;
import com.jf.entity.PropertyRightAppealLogExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightAppealLogBiz extends BaseService<PropertyRightAppealLog, PropertyRightAppealLogExample> {

	@Autowired
	private PropertyRightAppealLogMapper dao;
	@Autowired
	private PropertyRightAppealLogExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightAppealLogMapper(PropertyRightAppealLogMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightAppealLogExt save(PropertyRightAppealLogExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightAppealLog update(PropertyRightAppealLog model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightAppealLog model = new PropertyRightAppealLog();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightAppealLogExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightAppealLogExt find(PropertyRightAppealLogExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightAppealLogExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightAppealLogExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightAppealLogExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightAppealLogExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightAppealLogExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightAppealLogExt> list(PropertyRightAppealLogExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightAppealLogExt> paginate(PropertyRightAppealLogExtExample example) {
		List<PropertyRightAppealLogExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
