package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CloudProductItemMapper;
import com.jf.dao.CloudProductItemExtMapper;
import com.jf.entity.CloudProductItem;
import com.jf.entity.CloudProductItemExample;
import com.jf.entity.CloudProductItemExt;
import com.jf.entity.CloudProductItemExtExample;
import com.jf.common.base.BaseService;

@Service
public class CloudProductItemBiz extends BaseService<CloudProductItem, CloudProductItemExample> {

	@Autowired
	private CloudProductItemMapper dao;
	@Autowired
	private CloudProductItemExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCloudProductItemMapper(CloudProductItemMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CloudProductItemExt save(CloudProductItemExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CloudProductItem update(CloudProductItem model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CloudProductItem model = new CloudProductItem();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CloudProductItemExt findById(int id){
		return extDao.findById(id);
	}

	public CloudProductItemExt find(CloudProductItemExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CloudProductItemExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CloudProductItemExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CloudProductItemExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CloudProductItemExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CloudProductItemExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CloudProductItemExt> list(CloudProductItemExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CloudProductItemExt> paginate(CloudProductItemExtExample example) {
		List<CloudProductItemExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
