package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.PropertyRightComplainPicExtMapper;
import com.jf.dao.PropertyRightComplainPicMapper;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;
import com.jf.entity.PropertyRightComplainPicExt;
import com.jf.entity.PropertyRightComplainPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyRightComplainPicBiz extends BaseService<PropertyRightComplainPic, PropertyRightComplainPicExample> {

	@Autowired
	private PropertyRightComplainPicMapper dao;
	@Autowired
	private PropertyRightComplainPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setPropertyRightComplainPicMapper(PropertyRightComplainPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public PropertyRightComplainPicExt save(PropertyRightComplainPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public PropertyRightComplainPic update(PropertyRightComplainPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		PropertyRightComplainPic model = new PropertyRightComplainPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public PropertyRightComplainPicExt findById(int id){
		return extDao.findById(id);
	}

	public PropertyRightComplainPicExt find(PropertyRightComplainPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(PropertyRightComplainPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, PropertyRightComplainPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, PropertyRightComplainPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, PropertyRightComplainPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(PropertyRightComplainPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<PropertyRightComplainPicExt> list(PropertyRightComplainPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<PropertyRightComplainPicExt> paginate(PropertyRightComplainPicExtExample example) {
		List<PropertyRightComplainPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
