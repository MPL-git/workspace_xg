package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CloudProductPropValueMapper;
import com.jf.dao.CloudProductPropValueExtMapper;
import com.jf.entity.CloudProductPropValue;
import com.jf.entity.CloudProductPropValueExample;
import com.jf.entity.CloudProductPropValueExt;
import com.jf.entity.CloudProductPropValueExtExample;
import com.jf.common.base.BaseService;

@Service
public class CloudProductPropValueBiz extends BaseService<CloudProductPropValue, CloudProductPropValueExample> {

	@Autowired
	private CloudProductPropValueMapper dao;
	@Autowired
	private CloudProductPropValueExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCloudProductPropValueMapper(CloudProductPropValueMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CloudProductPropValueExt save(CloudProductPropValueExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CloudProductPropValue update(CloudProductPropValue model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CloudProductPropValue model = new CloudProductPropValue();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CloudProductPropValueExt findById(int id){
		return extDao.findById(id);
	}

	public CloudProductPropValueExt find(CloudProductPropValueExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CloudProductPropValueExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CloudProductPropValueExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CloudProductPropValueExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CloudProductPropValueExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CloudProductPropValueExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CloudProductPropValueExt> list(CloudProductPropValueExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CloudProductPropValueExt> paginate(CloudProductPropValueExtExample example) {
		List<CloudProductPropValueExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
