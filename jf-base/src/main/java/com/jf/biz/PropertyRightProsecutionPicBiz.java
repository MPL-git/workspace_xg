package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightProsecutionPicExtMapper;
import com.jf.dao.PropertyRightProsecutionPicMapper;
import com.jf.entity.PropertyRightProsecutionPic;
import com.jf.entity.PropertyRightProsecutionPicExample;
import com.jf.entity.PropertyRightProsecutionPicExt;
import com.jf.entity.PropertyRightProsecutionPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightProsecutionPicBiz extends BaseService<PropertyRightProsecutionPic, PropertyRightProsecutionPicExample> {

	@Autowired
	private PropertyRightProsecutionPicMapper dao;
	@Autowired
	private PropertyRightProsecutionPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightProsecutionPicMapper(PropertyRightProsecutionPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightProsecutionPicExt save(PropertyRightProsecutionPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightProsecutionPic update(PropertyRightProsecutionPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightProsecutionPic model = new PropertyRightProsecutionPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightProsecutionPicExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightProsecutionPicExt find(PropertyRightProsecutionPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightProsecutionPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightProsecutionPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightProsecutionPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightProsecutionPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightProsecutionPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightProsecutionPicExt> list(PropertyRightProsecutionPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightProsecutionPicExt> paginate(PropertyRightProsecutionPicExtExample example) {
		List<PropertyRightProsecutionPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
