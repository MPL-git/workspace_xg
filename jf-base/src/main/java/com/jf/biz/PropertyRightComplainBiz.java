package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightComplainExtMapper;
import com.jf.dao.PropertyRightComplainMapper;
import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainExample;
import com.jf.entity.PropertyRightComplainExt;
import com.jf.entity.PropertyRightComplainExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightComplainBiz extends BaseService<PropertyRightComplain, PropertyRightComplainExample> {

	@Autowired
	private PropertyRightComplainMapper dao;
	@Autowired
	private PropertyRightComplainExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightComplainMapper(PropertyRightComplainMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightComplainExt save(PropertyRightComplainExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightComplain update(PropertyRightComplain model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightComplain model = new PropertyRightComplain();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightComplainExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightComplainExt find(PropertyRightComplainExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightComplainExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightComplainExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightComplainExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightComplainExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightComplainExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightComplainExt> list(PropertyRightComplainExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightComplainExt> paginate(PropertyRightComplainExtExample example) {
		List<PropertyRightComplainExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
