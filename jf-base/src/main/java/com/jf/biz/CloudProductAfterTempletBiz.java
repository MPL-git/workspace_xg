package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.CloudProductAfterTempletMapper;
import com.jf.dao.CloudProductAfterTempletExtMapper;
import com.jf.entity.CloudProductAfterTemplet;
import com.jf.entity.CloudProductAfterTempletExample;
import com.jf.entity.CloudProductAfterTempletExt;
import com.jf.entity.CloudProductAfterTempletExtExample;
import com.jf.common.base.BaseService;

@Service
public class CloudProductAfterTempletBiz extends BaseService<CloudProductAfterTemplet, CloudProductAfterTempletExample> {

	@Autowired
	private CloudProductAfterTempletMapper dao;
	@Autowired
	private CloudProductAfterTempletExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCloudProductAfterTempletMapper(CloudProductAfterTempletMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CloudProductAfterTempletExt save(CloudProductAfterTempletExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CloudProductAfterTemplet update(CloudProductAfterTemplet model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CloudProductAfterTemplet model = new CloudProductAfterTemplet();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CloudProductAfterTempletExt findById(int id){
		return extDao.findById(id);
	}

	public CloudProductAfterTempletExt find(CloudProductAfterTempletExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CloudProductAfterTempletExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CloudProductAfterTempletExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CloudProductAfterTempletExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CloudProductAfterTempletExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CloudProductAfterTempletExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CloudProductAfterTempletExt> list(CloudProductAfterTempletExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CloudProductAfterTempletExt> paginate(CloudProductAfterTempletExtExample example) {
		List<CloudProductAfterTempletExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
