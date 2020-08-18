package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.AppVersionMapper;
import com.jf.dao.AppVersionExtMapper;
import com.jf.entity.AppVersion;
import com.jf.entity.AppVersionExample;
import com.jf.entity.AppVersionExt;
import com.jf.entity.AppVersionExtExample;
import com.jf.common.base.BaseService;

@Service
public class AppVersionBiz extends BaseService<AppVersion, AppVersionExample> {

	@Autowired
	private AppVersionMapper dao;
	@Autowired
	private AppVersionExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppVersionMapper(AppVersionMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppVersionExt save(AppVersionExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppVersion update(AppVersion model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppVersion model = new AppVersion();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppVersionExt findById(int id){
		return extDao.findById(id);
	}

	public AppVersionExt find(AppVersionExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppVersionExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppVersionExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppVersionExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppVersionExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppVersionExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppVersionExt> list(AppVersionExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppVersionExt> paginate(AppVersionExtExample example) {
		List<AppVersionExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
