package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CloudProductMapper;
import com.jf.dao.CloudProductExtMapper;
import com.jf.entity.CloudProduct;
import com.jf.entity.CloudProductExample;
import com.jf.entity.CloudProductExt;
import com.jf.entity.CloudProductExtExample;
import com.jf.common.base.BaseService;

@Service
public class CloudProductBiz extends BaseService<CloudProduct, CloudProductExample> {

	@Autowired
	private CloudProductMapper dao;
	@Autowired
	private CloudProductExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCloudProductMapper(CloudProductMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CloudProductExt save(CloudProductExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CloudProduct update(CloudProduct model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CloudProduct model = new CloudProduct();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CloudProductExt findById(int id){
		return extDao.findById(id);
	}

	public CloudProductExt find(CloudProductExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CloudProductExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CloudProductExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CloudProductExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CloudProductExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CloudProductExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CloudProductExt> list(CloudProductExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CloudProductExt> paginate(CloudProductExtExample example) {
		List<CloudProductExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
