package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CloudProductPropMapper;
import com.jf.dao.CloudProductPropExtMapper;
import com.jf.entity.CloudProductProp;
import com.jf.entity.CloudProductPropExample;
import com.jf.entity.CloudProductPropExt;
import com.jf.entity.CloudProductPropExtExample;
import com.jf.common.base.BaseService;

@Service
public class CloudProductPropBiz extends BaseService<CloudProductProp, CloudProductPropExample> {

	@Autowired
	private CloudProductPropMapper dao;
	@Autowired
	private CloudProductPropExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCloudProductPropMapper(CloudProductPropMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CloudProductPropExt save(CloudProductPropExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CloudProductProp update(CloudProductProp model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CloudProductProp model = new CloudProductProp();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CloudProductPropExt findById(int id){
		return extDao.findById(id);
	}

	public CloudProductPropExt find(CloudProductPropExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CloudProductPropExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CloudProductPropExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CloudProductPropExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CloudProductPropExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CloudProductPropExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CloudProductPropExt> list(CloudProductPropExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CloudProductPropExt> paginate(CloudProductPropExtExample example) {
		List<CloudProductPropExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
