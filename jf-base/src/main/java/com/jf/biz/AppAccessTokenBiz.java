package com.jf.biz;

import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.jf.dao.AppAccessTokenMapper;
import com.jf.dao.AppAccessTokenExtMapper;
import com.jf.entity.AppAccessToken;
import com.jf.entity.AppAccessTokenExample;
import com.jf.entity.AppAccessTokenExt;
import com.jf.entity.AppAccessTokenExtExample;
import com.jf.common.base.BaseService;

@Service
public class AppAccessTokenBiz extends BaseService<AppAccessToken, AppAccessTokenExample> {

	@Autowired
	private AppAccessTokenMapper dao;
	@Autowired
	private AppAccessTokenExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAppAccessTokenMapper(AppAccessTokenMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AppAccessTokenExt save(AppAccessTokenExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public AppAccessToken update(AppAccessToken model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		AppAccessToken model = new AppAccessToken();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public AppAccessTokenExt findById(int id){
		return extDao.findById(id);
	}

	public AppAccessTokenExt find(AppAccessTokenExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AppAccessTokenExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AppAccessTokenExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AppAccessTokenExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AppAccessTokenExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AppAccessTokenExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AppAccessTokenExt> list(AppAccessTokenExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AppAccessTokenExt> paginate(AppAccessTokenExtExample example) {
		List<AppAccessTokenExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
