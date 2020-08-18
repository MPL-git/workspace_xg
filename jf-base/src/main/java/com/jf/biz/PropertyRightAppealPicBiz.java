package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightAppealPicExtMapper;
import com.jf.dao.PropertyRightAppealPicMapper;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import com.jf.entity.PropertyRightAppealPicExt;
import com.jf.entity.PropertyRightAppealPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightAppealPicBiz extends BaseService<PropertyRightAppealPic, PropertyRightAppealPicExample> {

	@Autowired
	private PropertyRightAppealPicMapper dao;
	@Autowired
	private PropertyRightAppealPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightAppealPicMapper(PropertyRightAppealPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightAppealPicExt save(PropertyRightAppealPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightAppealPic update(PropertyRightAppealPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightAppealPic model = new PropertyRightAppealPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightAppealPicExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightAppealPicExt find(PropertyRightAppealPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightAppealPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightAppealPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightAppealPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightAppealPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightAppealPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightAppealPicExt> list(PropertyRightAppealPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightAppealPicExt> paginate(PropertyRightAppealPicExtExample example) {
		List<PropertyRightAppealPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
